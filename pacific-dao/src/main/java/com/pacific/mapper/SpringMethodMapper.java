package com.pacific.mapper;

import com.pacific.domain.dto.report.SpringMethodReportDto;
import com.pacific.domain.entity.SpringMethod;
import com.pacific.domain.search.query.SpringMethodQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SpringMethodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpringMethod record);

    int insertSelective(SpringMethod record);

    SpringMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpringMethod record);

    int updateByPrimaryKey(SpringMethod record);

    int batchSaveSpringMethod(List<SpringMethod> springMethodList);

    List<SpringMethodReportDto> selectByParam(SpringMethodQuery springMethodQuery);

    int  getTotalByParam(SpringMethodQuery springMethodQuery);

    SpringMethod querySpringMethodByParam(@Param("applicationCode") String applicationCode,
                                 @Param("hostName") String hostName,
                                 @Param("beginDate") Date beginDate,
                                 @Param("endDate") Date endDate,
                                 @Param("method")String method);

    List<SpringMethod> querySpringMethodErrorByParam(@Param("applicationCode") String applicationCode,
                                         @Param("hostName") String hostName,
                                         @Param("beginDate") Date beginDate,
                                         @Param("endDate") Date endDate,
                                         @Param("method")String method);

    List<SpringMethod> queryHasNoAlarmErrorSpringMethod();

    int queryErrorTotalSm(@Param("beginDate")Date beginDate,
                          @Param("endDate") Date endDate);
}