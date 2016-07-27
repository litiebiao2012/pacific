package com.pacific.service;

import com.pacific.domain.dto.jvm.SpringMethodInfo;
import com.pacific.domain.dto.report.SpringMethodDetailDto;
import com.pacific.domain.dto.report.SpringMethodReportDto;
import com.pacific.domain.entity.SpringMethod;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SpringMethodQuery;

import java.util.List;

/**
 * Created by Fe on 16/7/13.
 */
public interface SpringMethodService {

    public void saveSpringMethodInfo(String appCode,String clientIp,String hostName,List<SpringMethodInfo> springMethodInfoList);

    public Pagination<SpringMethodReportDto> querySpringMethodReport(SpringMethodQuery springMethodQuery);

    public SpringMethodDetailDto buildSpringMethodDetailDto(String hostName, String timeInternal, String applicationCode, String method);

}
