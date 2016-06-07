package com.pacific.web.controller;

import com.pacific.common.web.xuser.XUser;
import com.pacific.common.web.xuser.XUserSessionManager;

/**
 * Created by Fe on 16/6/7.
 */
public class BaseController {

    protected void refreshXUser(XUser xUser) {
        XUserSessionManager.getCurrent().setXUser(xUser);
    }

    public  XUser getXUser() {
        return XUserSessionManager.getCurrent().getXUser();
    }

    public void addAttrToXUserSession(String key,Object value) {
        XUserSessionManager.getCurrent().setAttr(key,value);
    }

    public String getRandomCode() {
        return XUserSessionManager.getCurrent().getRandomCode();
    }


    public String getToken() {
        return XUserSessionManager.getCurrent().getToken();
    }
}
