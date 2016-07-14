package com.pacific.web.tool;

import com.pacific.common.spring.SpringContext;
import com.pacific.common.web.RequestContext;
import com.pacific.common.web.xuser.XUser;
import com.pacific.common.web.xuser.XUserSessionManager;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.User;
import com.pacific.domain.enums.ChannelCodeEnums;
import com.pacific.domain.enums.RoleTypeEnums;
import com.pacific.domain.enums.StateEnums;
import com.pacific.mapper.UserMapper;
import com.pacific.service.ApplicationService;
import com.pacific.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Role;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/6/7.
 */
public class PacificWebTool {

    public static final Map<String, String> uriDescriptionMapping = new HashMap<String, String>();

    static {
        uriDescriptionMapping.put("/home.htm", "日志大盘");
        uriDescriptionMapping.put("/user/userList.htm", "用户列表");
        uriDescriptionMapping.put("/application/list.htm", "应用列表");
        uriDescriptionMapping.put("/log/list.htm", "日志列表");
        uriDescriptionMapping.put("/alarmLog/list.htm", "告警记录");
        uriDescriptionMapping.put("/application/edit.htm", "应用添加");
        uriDescriptionMapping.put("/user/editUser.htm", "用户编辑");
        uriDescriptionMapping.put("/user/userUpdatePass.htm", "密码修改");
        uriDescriptionMapping.put("/jvm/jvmDetail.htm", "应用监控");
    }

    public boolean hasPermission() {
        boolean flag = false;
        Long uid = XUserSessionManager.getCurrent().getXUser().getUid();
        if (uid != null) {
            UserService userService = SpringContext.getBean(UserService.class);
            User user = userService.queryUserById(uid);
            if (user != null) {
                if (RoleTypeEnums.fromCode(user.getRoleType()).getCode().equals(RoleTypeEnums.ADMIN.getCode())) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public String getDescriptionByUri() {
        String requestUri = RequestContext.getRequest().getRequestURI();
        return uriDescriptionMapping.get(requestUri);
    }

    public String getLoginUserName() {
        String userName = null;
        if (XUserSessionManager.getCurrent() != null) {
            XUser xUser = XUserSessionManager.getCurrent().getXUser();
            if (xUser != null) {
                userName = xUser.getUserName();
            }
        }
        return userName;
    }

    public String processNum(Long num) {

        if (num == null) {
            return StringUtils.EMPTY;
        }

        StringBuffer numSb = new StringBuffer();
        if (num > 99999) {
            String str = num + "";
            return numSb.append(str.substring(0, 5)).append("万").toString();
        } else {
            return num + "";
        }
    }

    public String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public String getChannelText(String channelCode) {
        String text = "";
        ChannelCodeEnums channelCodeEnums = ChannelCodeEnums.fromCode(channelCode);
        if (channelCodeEnums != null) {
            text = channelCodeEnums.getText();
        }
        return text;
    }

    public List<Application> queryAllApplication(){
        ApplicationService applicationService = SpringContext.getBean(ApplicationService.class);
        return applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
    }
}
