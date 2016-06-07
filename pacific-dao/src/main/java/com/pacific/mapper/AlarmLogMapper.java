package com.pacific.mapper;

import com.pacific.domain.entity.AlarmLog;

public interface AlarmLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlarmLog record);

    int insertSelective(AlarmLog record);

    AlarmLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmLog record);

    int updateByPrimaryKey(AlarmLog record);
}