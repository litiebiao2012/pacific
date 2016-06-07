package com.pacific.service;

import com.pacific.domain.dto.UserDto;
import com.pacific.domain.entity.User;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.query.UserQuery;

import java.util.List;

/**
 * Created by Fe on 16/6/7.
 */
public interface UserService {

    public User queryUserByAccount(String account);

    public Pagination<UserDto> queryAllUserPage(UserQuery userQuery);

}
