package com.pacific.mapper;

import com.pacific.domain.entity.JdbcInfo;

public interface JdbcInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdbcInfo record);

    int insertSelective(JdbcInfo record);

    JdbcInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdbcInfo record);

    int updateByPrimaryKey(JdbcInfo record);
}