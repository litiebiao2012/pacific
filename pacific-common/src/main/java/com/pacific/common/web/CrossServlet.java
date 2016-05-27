package com.pacific.common.web;


import com.pacific.common.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cros跨域servlet
 * Created by Fe on 16/3/28.
 */
public class CrossServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieUtil.setOptionCrossCookies(resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieUtil.setCrossCookies(resp);
    }
}
