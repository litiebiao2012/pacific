package com.pacific.mapper;

import com.pacific.domain.entity.User;
import com.pacific.domain.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryUserByAccount(@Param("type") String type, @Param("account") String account);

    List<User> queryUserListPageByParam(UserQuery userQuery);

    Integer queryUserListCount(UserQuery userQuery);
}