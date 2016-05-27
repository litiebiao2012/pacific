package com.pacific.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangjunling on 2015年7月8日.
 */

public class CookieUtil {

    /**
     * 根据cookie的名称获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                //&& request.getServerName().equals(cookies[i].getDomain())) {
                return cookies[i];
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie ck = getCookie(request, name);
        if (ck != null) {
            return ck.getValue();
        } else {
            return null;
        }
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath(getPath(request));
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 设置cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 如果不设置时间，默认永久
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        setCookie(request, response, name, value, 0x278d00);
    }

    /**
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * 设置cookie，设定时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value == null ? "" : value.replaceAll("\r\n", ""));
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(request));
        response.addCookie(cookie);
    }

    private static String getPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return (path == null || path.length() == 0) ? "/" : path;
    }

    public static void setCrossCookies(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("P3P", "CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR");
    }

    public static void setOptionCrossCookies(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
    }
}
