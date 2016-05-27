package com.pacific.common.web.xuser;

import com.pacific.common.Constants;
import com.pacific.common.http.HttpUtils;
import com.pacific.common.json.FastJson;
import com.pacific.common.spring.SpringContext;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.utils.IpUtils;
import com.pacific.common.utils.Randoms;
import com.pacific.common.web.RequestContext;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class XUserSession {

    private static final long serialVersionUID = -8889430465884234259L;
    private String              token;
    private XUser               xUser;
    private Map<String, Object> attrs;
    private String              csrfToken;
    /** 创建时间 **/
    private String              createTime;
    /** 请求客户端ip **/
    private String              clientIp;
    /** 产生session对应的服务器ip **/
    private String              serverIp;
    private static String       RANDOM_CODE_KEY = "_RANDOM_CODE_KEY";

    public XUserSession() {
        token = Constants.APP_NAME + "_" + Randoms.UUID();
        xUser = new XUser();
        attrs = new HashMap<>();
        createTime = DateUtil.formatDateTime(new Date());
        clientIp = HttpUtils.getIP4(RequestContext.getRequest());
        serverIp = IpUtils.getRealIp();
        csrfToken = RandomStringUtils.randomAlphanumeric(8);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setAttr(String name, Object object) {
        attrs.put(name, object);
    }

    public <T> T getAttr(String name) {
        return (T) attrs.get(name);
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public XUser getXUser() {
        return xUser;
    }

    /**
     * 返回当前用户, 永远不为空
     * @return
     */
    public static XUserSession getCurrent() {
         return XUserSessionManager.getCurrent();
    }

    /**
     * 初始化当前用户的session
     */
    public static void initXUserSession() {
        XUserSessionManager.getCurrent();
    }

    public static void refreshXUser(XUser xUser) {
        XUserSessionManager.refreshXUser(xUser);
    }

    public static void login(XUser xUser) {
        xUser.setIsSignedIn(true);
        refreshXUser(xUser);
    }

    public static void logout() {
        XUser xUser = getCurrent().getXUser();
        xUser.setIsSignedIn(false);
        xUser.setIsSavePass(false);

        refreshXUser(xUser);
    }

    public void setXUser(XUser xUser) {
        this.xUser = xUser;
    }

    public void setRandomCode(String randomCode) {
        setAttr(RANDOM_CODE_KEY, randomCode);
    }

    /**
     * 用户图片验证码
     * @return
     */
    public String getRandomCode() {
        return getAttr(RANDOM_CODE_KEY);
    }

    public void refresh() {

    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void refreshRemoteCache() {
        RedisTemplate redisTemplate = SpringContext.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set(getToken(), FastJson.toJson(this), Constants.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
    }

}
