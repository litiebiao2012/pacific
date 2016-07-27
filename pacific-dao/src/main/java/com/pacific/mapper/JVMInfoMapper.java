package com.pacific.mapper;

import com.pacific.domain.entity.JVMInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JVMInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JVMInfo record);

    int insertSelective(JVMInfo record);

    JVMInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JVMInfo record);

    int updateByPrimaryKey(JVMInfo record);

    int updateByParam(JVMInfo jvmInfo);

    List<JVMInfo> selectByParam(@Param("applicationCode") String applicationCode, @Param("hostName") String hostName);


}