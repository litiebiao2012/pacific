package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.common.web.xuser.XUser;
import com.pacific.common.web.xuser.XUserSessionManager;
import com.pacific.domain.entity.User;
import com.pacific.domain.enums.StateEnums;
import com.pacific.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/6/7.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @LoginCheckAnnotation(checked = false)
    @ResponseBody
    @RequestMapping("/doLogin.json")
    public AjaxResult doLogin(String account,String password) {
        User user = userService.queryUserByAccount(account);
        AjaxResult ajaxResult = new AjaxResult();
        if (user == null) {
            ajaxResult.setErrorMessage("用户不存在,请联系管理员添加!");
            return ajaxResult;
        }

        if (user.getState().equals(StateEnums.DISABLES.getCode())) {
            ajaxResult.setErrorMessage("用户已被禁用,无法登陆!");
            return ajaxResult;
        }

        if (!user.getPassword().equals(password)) {
            ajaxResult.setErrorMessage("密码错误,请重新登陆!");
            return ajaxResult;
        }

        XUser xUser = new XUser();
        xUser.setIsSignedIn(true);
        xUser.setUid(user.getId());
        xUser.setUserName(xUser.getPassword());
        refreshXUser(xUser);
        return ajaxResult;
    }

    @RequestMapping(value = "/userList.htm",method = RequestMethod.GET)
    public String toUserList() {
        return "user/userList";
    }

    @RequestMapping(value = "/userList.htm",method = RequestMethod.POST)
    public AjaxResult doUserList() {

        return null;
    }
}
