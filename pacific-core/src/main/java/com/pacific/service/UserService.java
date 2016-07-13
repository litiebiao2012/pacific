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

    public User queryUserById(Long id);

    public Pagination<UserDto> queryAllUserPage(UserQuery userQuery);

    public void saveUser(User user);

    public void updatePass(Long userId,String oldPass,String newPass);
}
