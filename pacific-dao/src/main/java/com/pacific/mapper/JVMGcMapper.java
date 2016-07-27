package com.pacific.mapper;

import com.pacific.domain.entity.JVMGc;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface JVMGcMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMGc record);

    int insertSelective(JVMGc record);

    JVMGc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMGc record);

    int updateByPrimaryKey(JVMGc record);

    JVMGc queryAllJVMGcByParam(@Param("applicationCode") String applicationCode,
                                       @Param("hostName") String hostName,
                                       @Param("beginDate") Date beginDate,
                                       @Param("endDate") Date endDate);
}