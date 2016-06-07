package com.pacific.service.impl;

import com.pacific.common.exception.PacificException;
import com.pacific.common.utils.SensitiveDataUtil;
import com.pacific.domain.entity.User;
import com.pacific.mapper.UserMapper;
import com.pacific.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/6/7.
 */
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

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
}
