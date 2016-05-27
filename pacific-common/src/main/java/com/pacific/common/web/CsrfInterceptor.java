package com.pacific.common.web;

import com.pacific.common.web.xuser.XUserSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by panwang.chengpw on 1/22/15.
 */
public class CsrfInterceptor extends HandlerInterceptorAdapter {

    private Logger logger     = LoggerFactory.getLogger(CsrfInterceptor.class);
    private static String CSRF_TOKEN = "csrfToken";
    private boolean       debugMode  = true;
    private List<String>  excludePath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("");
        }

        if (isCheckCsrfToken(request)) {
            boolean isPass = checkCsrfToken(request);
            return isPass || debugMode;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    private boolean isCheckCsrfToken(HttpServletRequest request) {
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "POST") || StringUtils.endsWith(request.getRequestURI(), ".json")) {
            return true;
        }

        return false;
    }

    private boolean checkCsrfToken(HttpServletRequest request) {
        return StringUtils.equals(request.getParameter(CSRF_TOKEN), XUserSession.getCurrent().getCsrfToken());
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
