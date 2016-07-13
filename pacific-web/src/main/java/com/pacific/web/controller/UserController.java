package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import com.pacific.common.utils.ValidateUtils;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.common.web.xuser.XUser;
import com.pacific.common.web.xuser.XUserSessionManager;
import com.pacific.domain.dto.UserDto;
import com.pacific.domain.entity.User;
import com.pacific.domain.enums.RoleTypeEnums;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.query.UserQuery;
import com.pacific.mapper.UserMapper;
import com.pacific.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Fe on 16/6/7.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

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
        xUser.setUserName(user.getUserName());
        refreshXUser(xUser);
        return ajaxResult;
    }

    @RequestMapping(value = "/loginOut.htm",method = RequestMethod.GET)
    public ModelAndView loginOut() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login.htm");
        return modelAndView;
    }

    @RequestMapping(value = "/userList.htm",method = RequestMethod.GET)
    public String toUserList() {
        return "user/userList";
    }

    @RequestMapping(value = "/editUser.htm",method = RequestMethod.GET)
    public ModelAndView toEditUser(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null) {
            User user = userMapper.selectByPrimaryKey(id);
            if (user == null) {
                modelAndView.setViewName("user/userList");
            } else {
                modelAndView.addObject("user",user);
                modelAndView.setViewName("user/userEdit");
            }
        } else {
            modelAndView.setViewName("user/userEdit");
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveUser.json")
    public AjaxResult saveUser(User user) {
        ModelAndView modelAndView = new ModelAndView();

        String userName = user.getUserName();
        String email = user.getEmail();
        String phone = user.getPhone();

        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(userName)) {
            ajaxResult.setErrorMessage("用户名不能为空!");
            return ajaxResult;
        }

        if (StringUtils.isEmpty(email)) {
            ajaxResult.setErrorMessage("邮箱不能为空!");
            return ajaxResult;
        }

        if (!ValidateUtils.isEmail(email)) {
            ajaxResult.setErrorMessage("邮箱格式错误!");
            return ajaxResult;
        }

        if (StringUtils.isEmpty(phone)) {
            ajaxResult.setErrorMessage("手机号能为空!");
            return ajaxResult;
        }

        if (!ValidateUtils.isMobile(phone)) {
            ajaxResult.setErrorMessage("手机号格式错误!");
            return ajaxResult;
        }
        user.setPassword("123456");
        user.setRoleType(RoleTypeEnums.PERSONAL.getCode());
        user.setUpdateTime(new Date());
        if (user.getId() == null) {
            user.setCreateTime(new Date());
            user.setState(StateEnums.AVAILABLE.getCode());
        }

        user.setUserName(userName);
        user.setPhone(phone);
        user.setEmail(email);

        userService.saveUser(user);

        modelAndView.addObject("user",user);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping(value = "/userList.json",method = RequestMethod.POST)
    public AjaxResult doUserList() {
        UserQuery userQuery = new UserQuery();
        Pagination<UserDto> userDtoPagination = userService.queryAllUserPage(userQuery);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(userDtoPagination);
        return ajaxResult;
    }

    @RequestMapping(value = "/userUpdatePass.htm",method = RequestMethod.GET)
    public ModelAndView toUserUpdatePass() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",getXUser().getUid());
        modelAndView.setViewName("user/userUpdatePass");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePass.json",method = RequestMethod.POST)
    public AjaxResult updatePass(String oldPass,String newPass) {
        AjaxResult ajaxResult = new AjaxResult();
        userService.updatePass(getXUser().getUid(),oldPass,newPass);
        ajaxResult.setMessage("修改成功!");
        return ajaxResult;
    }

}

