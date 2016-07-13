package com.pacific.mapper;

import com.pacific.domain.entity.SpringMethod;

public interface SpringMethodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpringMethod record);

    int insertSelective(SpringMethod record);

    SpringMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpringMethod record);

    int updateByPrimaryKey(SpringMethod record);
}