package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;
import com.pacific.domain.dto.report.SqlDetailDto;
import com.pacific.domain.dto.report.SqlDetailReportDto;
import com.pacific.domain.dto.report.SqlErrorDetail;
import com.pacific.domain.dto.report.SqlReportDto;
import com.pacific.domain.entity.Sql;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SqlQuery;
import com.pacific.mapper.SqlMapper;
import com.pacific.service.EChartHelper;
import com.pacific.service.SqlService;
import com.pacific.service.TimeInternalHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/7/20.
 */
public class SqlServiceImpl implements SqlService {

    @Resource
    private SqlMapper sqlMapper;

    public void saveSqlInfo(String appCode,String clientIp,String hostName,List<SqlInfo> sqlInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(sqlInfoList);

        List<Sql> sqlList = new ArrayList<Sql>();
        for (SqlInfo sqlInfo : sqlInfoList) {
            Sql sql = new Sql();
            sql.setCreateTime(new Date());
            sql.setUpdateTime(new Date());
            sql.setApplicationCode(appCode);
            sql.setClientIp(clientIp);
            sql.setHostName(hostName);
            sql.setConcurrentMax(sqlInfo.getConcurrentMax());
            sql.setCount(sqlInfo.getCount());
            sql.setEffectedRowCount(sqlInfo.getEffectedRowCount());
            sql.setEffectedRowCountMax(sqlInfo.getEffectedRowCountMax());
            sql.setFetchRowCount(sqlInfo.getFetchRowCount());
            sql.setFetchRowCountMax(sqlInfo.getFetchRowCountMax());
            sql.setMaxTime(sqlInfo.getMaxTime());
            sql.setSqlText(sqlInfo.getSql());
            sql.setSqlHash(sqlInfo.getSqlHash());
            sql.setTotalTime(sqlInfo.getTotalTime());
            sql.setUrl(sqlInfo.getUrl());

            if (StringUtils.isNotEmpty(sqlInfo.getLastErrorMsg())) {
                sql.setIsError("y");
                sql.setErrorMsg(sqlInfo.getLastErrorMsg());
                sql.setErrorTime(sqlInfo.getLastErrorTime());
            } else {
                sql.setIsError("n");
            }
            sqlList.add(sql);
        }
        sqlMapper.batchSaveSql(sqlList);
    }

    public Pagination<SqlReportDto> querySqlReportPage(SqlQuery sqlQuery) {
        Assert.notNull(sqlQuery);
        if (sqlQuery.getHostName() != null && sqlQuery.getHostName().equals("all")) sqlQuery.setHostName(null);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,sqlQuery.getTimeInternal());
        sqlQuery.setBeginDate(beginDate);
        sqlQuery.setEndDate(endDate);

        List<SqlReportDto> sqlReportDtoList = sqlMapper.selectByParam(sqlQuery);
        int total = sqlMapper.getTotalByParam(sqlQuery);
        Pagination<SqlReportDto> sqlReportDtoPagination = new Pagination<SqlReportDto>(sqlQuery,sqlReportDtoList,total);
        return sqlReportDtoPagination;
    }

    public SqlDetailDto buildSqlDetailDto(String hostName, String timeInternal, String applicationCode, String sqlHash) {
        Assert.notNull(hostName);
        Assert.notNull(timeInternal);
        Assert.notNull(applicationCode);
        Assert.notNull(sqlHash);

        if(hostName.equals("all")) hostName = null;

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        SqlDetailReportDto sqlDetailReportDto = new SqlDetailReportDto();

        sqlDetailReportDto.setxAxis(EChartHelper.buildXAxis(timeRangeDto.getFormatTimeList()));
        sqlDetailReportDto.setyAxis(EChartHelper.buildYAxis());
        sqlDetailReportDto.setLegend(EChartHelper.buildLegend("访问次数","最大并发"));

        List<Long> totalList = new LinkedList<Long>();
        List<Long> concurrentMaxList = new LinkedList<>();
        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        for (TimeRange timeRange : timeRangeList) {
            Sql sql = sqlMapper.queryAlSqlByParam(applicationCode,hostName,timeRange.getBeginDate(),timeRange.getEndDate(),sqlHash);

            if (sql != null) {
                totalList.add(sql.getCount());
                concurrentMaxList.add((long)sql.getConcurrentMax());
            } else {
                totalList.add(0l);
                concurrentMaxList.add(0l);
            }
        }
        List<Map<String,Object>> sqlDetailSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> countMap = new HashMap<String,Object>();
        countMap.put("name","访问次数");
        countMap.put("type","line");
        countMap.put("stack","总量");
        countMap.put("data",totalList);
        sqlDetailSeries.add(countMap);

        Map<String,Object> concurrentMaxMap = new HashMap<String,Object>();
        concurrentMaxMap.put("name","最大并发");
        concurrentMaxMap.put("type","line");
        concurrentMaxMap.put("stack","总量");
        concurrentMaxMap.put("data",concurrentMaxList);
        sqlDetailSeries.add(concurrentMaxMap);
        sqlDetailReportDto.setSeries(sqlDetailSeries);

        SqlDetailDto sqlDetailDto = new SqlDetailDto();
        sqlDetailDto.setSqlDetailReportDto(sqlDetailReportDto);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,timeInternal);
        List<Sql> sqlList = sqlMapper.querySqlErrorByParam(applicationCode,hostName,beginDate,endDate,sqlHash);
        List<SqlErrorDetail> sqlErrorDetailList = new LinkedList<SqlErrorDetail>();
        if (CollectionUtil.isNotEmpty(sqlList)) {
            for (Sql sql : sqlList) {
                SqlErrorDetail sqlErrorDetail = new SqlErrorDetail();
                sqlErrorDetail.setErrorDateTime(sql.getErrorTime());
                sqlErrorDetail.setErrorMsg(sql.getErrorMsg());
                sqlErrorDetailList.add(sqlErrorDetail);
            }
        }
        sqlDetailDto.setSqlErrorDetailList(sqlErrorDetailList);
        return sqlDetailDto;
    }
}
