package com.pacific.mapper;

import com.pacific.domain.entity.JVMGc;

public interface JVMGcMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMGc record);

    int insertSelective(JVMGc record);

    JVMGc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMGc record);

    int updateByPrimaryKey(JVMGc record);
}