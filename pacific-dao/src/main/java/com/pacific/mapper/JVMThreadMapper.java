package com.pacific.mapper;

import com.pacific.domain.entity.JVMThread;

public interface JVMThreadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMThread record);

    int insertSelective(JVMThread record);

    JVMThread selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMThread record);

    int updateByPrimaryKey(JVMThread record);
}