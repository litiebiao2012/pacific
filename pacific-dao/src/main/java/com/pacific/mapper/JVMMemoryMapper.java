package com.pacific.mapper;

import com.pacific.domain.entity.JVMMemory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface JVMMemoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMMemory record);

    int insertSelective(JVMMemory record);

    JVMMemory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMMemory record);

    int updateByPrimaryKey(JVMMemory record);

    JVMMemory queryAllJVMMemoryByParam(@Param("applicationCode") String applicationCode,
                                             @Param("clientIp") String clientIp,
                                             @Param("beginDate") Date beginDate,
                                             @Param("endDate") Date endDate);

}