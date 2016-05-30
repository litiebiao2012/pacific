package com.pacific.mapper;

import com.pacific.domain.entity.Application;

import java.util.List;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    List<Application> queryAllApplicationByState(String state);
}