package com.pacific.common.web;

import com.pacific.common.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chengpanwang on 7/9/15.
 */
public class Cookies {

    private static Logger logger = LoggerFactory.getLogger(Cookies.class);

    private static String BASE_DOMAIN;
    private static int    EXPIRE = 2 * 30 * 24 * 60 * 60;

    public static void setCookie(String name, String value) {
        setCookie(name, value, EXPIRE);
    }

    public static void setCookie(String name, String value, int expire) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(Constants.BASE_DOMAIN);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        RequestContext.getResponse().addCookie(cookie);
    }

    public static String getCookie(String name) {
        HttpServletRequest request = RequestContext.getRequest();
        if (request == null) {
            return StringUtils.EMPTY;
        }

        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return StringUtils.EMPTY;
        }

        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }

        return StringUtils.EMPTY;
    }
}
