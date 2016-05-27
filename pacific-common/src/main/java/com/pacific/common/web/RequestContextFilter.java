package com.pacific.common.web;

import com.pacific.common.http.HttpUtils;
import com.pacific.common.web.xuser.XUserSessionManager;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chengpanwang on 7/17/15.
 */
public class RequestContextFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(RequestContextFilter.class);
    private Boolean isOpenRefreshRemoteCache;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        isOpenRefreshRemoteCache=    BooleanUtils.toBoolean(filterConfig.getInitParameter("isOpenRefreshRemoteCache"));
        if(isOpenRefreshRemoteCache==null){
            isOpenRefreshRemoteCache=true;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestContext.init((HttpServletRequest) request, (HttpServletResponse) response, null);

        logger.debug(HttpUtils.requestMessage((HttpServletRequest) request));
        chain.doFilter(request, response);
        try {
            if(isOpenRefreshRemoteCache) {
                XUserSessionManager.refreshRemoteCache();
            }
        } catch (Exception e) {
            logger.error("refreshRemoteCache error !,e : {}",e);
        }
        try {
            RequestContext.clear();
        } catch (Exception e) {
            logger.error("RequestContext clear error ! , e : {}",e);
        }

    }

    @Override
    public void destroy() {

    }
}
