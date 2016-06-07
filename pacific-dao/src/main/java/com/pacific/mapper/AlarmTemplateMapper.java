package com.pacific.mapper;

import com.pacific.domain.entity.AlarmTemplate;

public interface AlarmTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlarmTemplate record);

    int insertSelective(AlarmTemplate record);

    AlarmTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmTemplate record);

    int updateByPrimaryKey(AlarmTemplate record);
}