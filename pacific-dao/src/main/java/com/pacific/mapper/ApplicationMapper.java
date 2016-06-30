package com.pacific.mapper;

import com.pacific.domain.dto.ApplicationDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.query.ApplicationQuery;

import java.util.List;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    List<Application> queryAllApplicationByState(String state);

    List<ApplicationDto> queryAllApplicationByParam(ApplicationQuery applicationQuery);

    int queryTotalApplication(ApplicationQuery applicationQuery);

    Application selectByApplicationCode(String applicationCode);
}