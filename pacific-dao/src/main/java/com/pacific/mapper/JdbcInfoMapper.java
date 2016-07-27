package com.pacific.mapper;

import com.pacific.domain.entity.JdbcInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdbcInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdbcInfo record);

    int insertSelective(JdbcInfo record);

    JdbcInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdbcInfo record);

    int updateByPrimaryKey(JdbcInfo record);

    JdbcInfo queryJdbcInfoByParam(@Param("applicationCode") String applicationCode,
                                  @Param("hostName")String hostName,
                                  @Param("url") String url);

    List<JdbcInfo> selectByParam(@Param("applicationCode") String applicationCode, @Param("hostName") String hostName);
}