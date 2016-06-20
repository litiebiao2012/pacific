package com.pacific.common.web.xuser;

import com.pacific.common.Constants;
import com.pacific.common.json.FastJson;
import com.pacific.common.spring.SpringContext;
import com.pacific.common.utils.CryptoUtil;
import com.pacific.common.web.Cookies;
import com.pacific.common.web.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;


/**
 * XUser 的全部实现, 要使用此方法需要实现XUserService 接口, 以断定用户合法性
 * 登录成功, 请执行@see init(XUser) 方法
 *
 * Created by panwang.chengpw on 5/11/15.
 */
public class XUserSessionManager {

    private static final Logger logger            = LoggerFactory.getLogger(XUserSessionManager.class);

    private static String       XUSER_SESSION_KEY = "xuser_session";
    private static String       CSRF_TOKEN_KEY    = "csrf_token";
    private static String       XUSER_NAME_KEY    = "xuser_n";
    private static String       XUSER_PASS_KEY    = "xuser_p";

    private static XUserService xUserService      = SpringContext.getBean(XUserService.class);

  //  private static RedisTemplate redisTemplate    = SpringContext.getBean(RedisTemplate.class);

    private static RedisTemplate redisTemplate;

    private static String STORE_TYPE_REDIS = "redis";
    private static String STORE_TYPE_NATIVE = "native";

    private static String TOKEN_KEY = Constants.APP_NAME + "-Token";

    public static boolean NEED_DEPEND_COOKIE = false;

    static {

    }

    private XUserSessionManager() {

    }

    /**
     * 取得当前用户XUserSession ,以后可以替换为redis等
     * @return 当前用户
     */
    private static XUserSession getXUserSession() {
        XUserSession xUserSession = null;
        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_NATIVE)) {
            HttpSession httpSession = RequestContext.getSession();
            if (httpSession != null) {
                xUserSession = (XUserSession) httpSession.getAttribute(XUSER_SESSION_KEY);
            } else {
                logger.error("http session is null , please check web app");
                throw new RuntimeException("http session is null , please check web app");
            }
        }

        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_REDIS)){
            String token = getToken();
            ValueOperations<String,String> redisSerializer = redisTemplate.opsForValue();
            if (StringUtils.isNotEmpty(token)) {
                 String userSessionJson = redisSerializer.get(token);
                if (StringUtils.isNotEmpty(userSessionJson)) {
                    xUserSession = FastJson.fromJson(userSessionJson,XUserSession.class);
                }
            }
        }
        return xUserSession;
    }

    private static void addTokenCookie(String token) {
        HttpServletResponse response = RequestContext.getResponse();
        Cookie cookie = new Cookie(TOKEN_KEY,token);
        response.addCookie(cookie);
    }

    /**
     * 存储当前用户XUserSession ,以后可以替换为redis等
     * @param xUserSession 当前用户sesseion
     */
    private static void setXUserSession(XUserSession xUserSession) {
        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_NATIVE)) {
            HttpSession httpSession = RequestContext.getSession();
            if (httpSession != null) {
                httpSession.setAttribute(XUSER_SESSION_KEY, xUserSession);
            } else {
                logger.error("http session is null , please check web app");
                throw new RuntimeException("http session is null , please check web app");
            }
        }

        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_REDIS)) {
            ValueOperations<String,String> redisSerializer = redisTemplate.opsForValue();
            String token = getToken();
            if (StringUtils.isEmpty(token)) token = xUserSession.getToken();
            redisSerializer.set(token,FastJson.toJson(xUserSession),Constants.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
        }
    }

    public static XUserSession getCurrent() {
        //TODO 先从上下文取
        XUserSession xUserSession = null;
        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_REDIS))  xUserSession = RequestContext.getXUserSession();

        //TODO 再从缓存区
        if (xUserSession == null) xUserSession = getXUserSession();

        if (xUserSession == null)  xUserSession = initXUserSession();

        if (Constants.SESSION_STORE_TYPE.equals(STORE_TYPE_REDIS))  RequestContext.setXUserSession(xUserSession);
        return xUserSession;
    }

    public static XUserSession initXUserSession() {
        XUserSession xUserSession = new XUserSession();
        XUser xUser = xUserSession.getXUser();

        if (Constants.NEED_DEPEND_COOKIE) {
            String name = Cookies.getCookie(XUSER_NAME_KEY);
            if (StringUtils.isNotBlank(name)) {
                xUser.setUserName(name);

                // 自动登录
                String password = Cookies.getCookie(XUSER_PASS_KEY);
                if (StringUtils.isNotBlank(password)) {
                    password = CryptoUtil.decryptAES(password, Constants.COOKIE_CRYPTO_PASS);
                    if (StringUtils.equals(password, xUserService.getPass(name))) {
                        xUser.setIsSignedIn(true);
                        xUser.setPassword(password);
                        xUser.setUid(xUserService.getUid(name));
                    }
                }
            }
        }
        xUserSession.setXUser(xUser);
        setXUserSession(xUserSession);

        if (Constants.NEED_DEPEND_COOKIE)  setCookies(xUserSession);

        return xUserSession;

    }

    public static void setCookies(XUserSession xUserSession) {
        if (Constants.NEED_DEPEND_COOKIE) {
            Cookies.setCookie(XUSER_NAME_KEY, xUserSession.getXUser().getUserName());
            Cookies.setCookie(CSRF_TOKEN_KEY, xUserSession.getCsrfToken());
            Cookies.setCookie(TOKEN_KEY,xUserSession.getToken());
            if (xUserSession.getXUser().getIsSavePass()) {
                Cookies.setCookie(XUSER_PASS_KEY, xUserSession.getXUser().getSecPassword());
            }
        }
    }

    /**
     * 登录成功时执行此方法, 标识已经登录
     * @param xuser 当前登录用户
     */
    public static void refreshXUser(XUser xuser) {
        if (xuser == null) {
            logger.error("init xuser , but xuser is null");
            return;
        }

        XUserSession xUserSession = XUserSession.getCurrent();
        xUserSession.setXUser(xuser);
        setCookies(xUserSession);
    }

    private static String getToken() {
        if (Constants.NEED_DEPEND_COOKIE) {
            return Cookies.getCookie(TOKEN_KEY);
        }
        else {
            String token = RequestContext.getRequest().getHeader(TOKEN_KEY);
            return token;
        }
    }

    public static void refreshRemoteCache() {
        getCurrent().refreshRemoteCache();
    }
}
