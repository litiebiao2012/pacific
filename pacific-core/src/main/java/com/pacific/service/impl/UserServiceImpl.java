package com.pacific.service.impl;

import com.pacific.common.exception.PacificException;
import com.pacific.common.json.FastJson;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.SensitiveDataUtil;
import com.pacific.domain.dto.UserDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.ApplicationUserConfig;
import com.pacific.domain.entity.User;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.query.UserQuery;
import com.pacific.mapper.ApplicationUserConfigMapper;
import com.pacific.mapper.UserMapper;
import com.pacific.service.ApplicationService;
import com.pacific.service.ApplicationUserConfigService;
import com.pacific.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/6/7.
 */
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationUserConfigService applicationUserConfigService;

    @Resource
    private ApplicationUserConfigMapper applicationUserConfigMapper;

    @Override
    public User queryUserByAccount(String account) {
        boolean flag = SensitiveDataUtil.isEmail(account);
        String type = "";
        if (flag) {
            type = "email";
        }
        flag = SensitiveDataUtil.isPhoneOrTelNo(account);
        if (flag) {
            type = "phone";
        }
        if (StringUtils.isEmpty(type)) PacificException.throwEx("账号格式错误!");

        return userMapper.queryUserByAccount(type,account);
    }

    @Override
    public Pagination<UserDto> queryAllUserPage(UserQuery userQuery) {
        Assert.notNull(userQuery);
        List<User> userList = userMapper.queryUserListPageByParam(userQuery);

        List<UserDto> userDtoList = null;
        if (CollectionUtil.isNotEmpty(userList)) {
            userDtoList = new ArrayList<UserDto>();
            for (User user : userList) {
                UserDto userDto = new UserDto();
                try {
                    BeanUtils.copyProperties(userDto,user);
                } catch (IllegalAccessException e) {
                    //TODO ignore
                } catch (InvocationTargetException e1) {
                    //TODO ignore
                }
                userDtoList.add(userDto);
            }
        }

        Integer total = userMapper.queryUserListCount(userQuery);

        Pagination<UserDto> pagination = new Pagination<UserDto>(userQuery,userDtoList,total);
        return pagination;
    }


    public void saveUser(User user) {
        Assert.notNull(user);
        if (user.getId() == null) {
            userMapper.insert(user);
            List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
            if (CollectionUtil.isNotEmpty(applicationList)) {
                List<ApplicationUserConfig> appUserConfigList = new ArrayList<ApplicationUserConfig>();
                for (Application app : applicationList) {
                    ApplicationUserConfig appUserConfig = new ApplicationUserConfig();
                    appUserConfig.setUserId(user.getId());
                    appUserConfig.setState(StateEnums.AVAILABLE.getCode());
                    appUserConfig.setUpdateTime(new Date());
                    appUserConfig.setCreateTime(new Date());
                    appUserConfig.setApplicationCode(app.getApplicationCode());
                    appUserConfig.setIsMonitorAllErrorLog("y");
                    appUserConfig.setChannelConfig(FastJson.toJson(ApplicationUserConfigServiceImpl.channelDtoList));

                    appUserConfigList.add(appUserConfig);
                }
                applicationUserConfigMapper.batchSaveApplicationUserConfig(appUserConfigList);
            }
        } else {
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
