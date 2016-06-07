package com.pacific.service;

import com.pacific.domain.entity.User;

/**
 * Created by Fe on 16/6/7.
 */
public interface UserService {

    public User queryUserByAccount(String account);
}
