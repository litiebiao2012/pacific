package com.pacific.mapper;

import com.pacific.domain.entity.JVMThread;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface JVMThreadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMThread record);

    int insertSelective(JVMThread record);

    JVMThread selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMThread record);

    int updateByPrimaryKey(JVMThread record);

    JVMThread queryAllJVMThreadByParam(@Param("applicationCode") String applicationCode,
                                       @Param("clientIp") String clientIp,
                                       @Param("beginDate") Date beginDate,
                                       @Param("endDate") Date endDate);
}

