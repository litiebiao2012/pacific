package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;
import com.pacific.domain.dto.report.*;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.Sql;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SqlQuery;
import com.pacific.mapper.SqlMapper;
import com.pacific.service.ApplicationService;
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

    @Resource
    private ApplicationService applicationService;

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

    public SqlAvgTimeReport queryDaySqlAvgTimeReport() {
        SqlAvgTimeReport sqlAvgTimeReport = new SqlAvgTimeReport();
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        if (CollectionUtil.isNotEmpty(applicationList)) {
            Map<String,String> titleMap = new HashMap<String,String>();
            titleMap.put("text","当日各应用sql执行平均响应时间统计");
            titleMap.put("subtext","数据汇总");
            titleMap.put("x","center");
            sqlAvgTimeReport.setTitle(titleMap);

            Map<String,String> tooltipMap = new HashMap<String,String>();
            tooltipMap.put("trigger","item");
            tooltipMap.put("formatter","{a} <br/>{b} : {c} ({d}%)");
            sqlAvgTimeReport.setTooltip(tooltipMap);


            Map<String,Object> legendMap = new HashMap<String,Object>();
            legendMap.put("orient","vertical");
            legendMap.put("left","left");

            List<String> appNameList = new LinkedList<String>();
            List<Map<String,Object>> seriesList = new ArrayList<Map<String,Object>>();
            Map<String,Object> seriesMap = new HashMap<String,Object>();

            for (Application app : applicationList) {
                appNameList.add(app.getApplicationName());
            }
            legendMap.put("data",appNameList);
            sqlAvgTimeReport.setLegend(legendMap);

            seriesMap.put("name","当日各应用sql执行平均响应时间统计");
            seriesMap.put("type","pie");
            seriesMap.put("radius","55%");
            seriesMap.put("center",Arrays.asList("50%","60%"));

            Date date = new Date();
            List<Map<String,Object>> appAvgMapList = sqlMapper.queryAvgSqlTimeByParam(DateUtil.getBeginTimeOfDay(date).toDate(),DateUtil.getEndTimeOfDay(date).toDate());
            seriesMap.put("data",buildSeriesDataList(appAvgMapList));

            Map<String,Object> itemStyleMap = new HashMap<String,Object>();
            Map<String,Object> emphasisMap = new HashMap<String,Object>();
            emphasisMap.put("shadowBlur",10);
            emphasisMap.put("shadowOffsetX",0);
            emphasisMap.put("shadowColor","rgba(0, 0, 0, 0.5)");
            itemStyleMap.put("emphasis",itemStyleMap);
            seriesMap.put("itemStyle",itemStyleMap);
            seriesList.add(seriesMap);
            sqlAvgTimeReport.setSeries(seriesList);
        }
        return sqlAvgTimeReport;
    }

    public List<Map<String,Object>> buildSeriesDataList(List<Map<String,Object>> appAvgMapList) {
        List<Map<String,Object>> seriesList = new ArrayList<Map<String,Object>>();
        if (appAvgMapList != null && appAvgMapList.size() > 0) {
            for (Map<String,Object> map : appAvgMapList) {
                Map<String,Object> newMap = new HashMap<String,Object>();
                newMap.put("value",map.get("avgTime"));
                newMap.put("name",map.get("applicationName"));

                seriesList.add(newMap);
            }
        }
        return seriesList;
    }


}
