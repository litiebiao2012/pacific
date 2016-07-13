package com.pacific.mapper;

import com.pacific.domain.entity.JVMMemory;

public interface JVMMemoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMMemory record);

    int insertSelective(JVMMemory record);

    JVMMemory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMMemory record);

    int updateByPrimaryKey(JVMMemory record);
}