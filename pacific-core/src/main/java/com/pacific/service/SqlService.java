package com.pacific.service;

import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;
import com.pacific.domain.dto.report.SqlAvgTimeReport;
import com.pacific.domain.dto.report.SqlDetailDto;
import com.pacific.domain.dto.report.SqlReportDto;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SqlQuery;

import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public interface SqlService {

    public void saveSqlInfo(String appCode,String clientIp,String hostName,List<SqlInfo> sqlInfoList);

    public Pagination<SqlReportDto> querySqlReportPage(SqlQuery sqlQuery);

    public SqlDetailDto buildSqlDetailDto(String hostName, String timeInternal, String applicationCode, String sqlHash);

    public SqlAvgTimeReport queryDaySqlAvgTimeReport();

}
