package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.SpringMethodInfo;
import com.pacific.domain.dto.report.SpringMethodDetailDto;
import com.pacific.domain.dto.report.SpringMethodDetailReportDto;
import com.pacific.domain.dto.report.SpringMethodErrorDetail;
import com.pacific.domain.dto.report.SpringMethodReportDto;
import com.pacific.domain.entity.SpringMethod;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SpringMethodQuery;
import com.pacific.mapper.SpringMethodMapper;
import com.pacific.service.EChartHelper;
import com.pacific.service.SpringMethodService;
import com.pacific.service.TimeInternalHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/7/13.
 */
public class SpringMethodServiceImpl implements SpringMethodService {
    @Resource
    private SpringMethodMapper springMethodMapper;

    @Override
    public void saveSpringMethodInfo(String appCode, String clientIp,String hostName,List<SpringMethodInfo> springMethodInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(springMethodInfoList);

        List<SpringMethod> springMethodList = new ArrayList<SpringMethod>();
        for (SpringMethodInfo springMethodInfo : springMethodInfoList) {
            SpringMethod springMethod = new SpringMethod();
            springMethod.setCreateTime(new Date());
            springMethod.setUpdateTime(new Date());
            springMethod.setApplicationCode(appCode);
            springMethod.setClientIp(clientIp);
            springMethod.setHostName(hostName);
            springMethod.setClassName(springMethodInfo.getClassName());
            springMethod.setMethod(springMethodInfo.getMethod());
            springMethod.setCount(springMethodInfo.getCount());
            springMethod.setConcurrentMax((long)springMethodInfo.getConcurrentMax());
            springMethod.setNanoMax(springMethodInfo.getNanoMax());
            springMethod.setNanoTotal(springMethodInfo.getNanoTotal());

            if (StringUtils.isNotEmpty(springMethodInfo.getLastErrorMsg())) {
                springMethod.setIsError("y");
            } else {
                springMethod.setIsError("n");
            }
            springMethod.setErrorMsg(springMethodInfo.getLastErrorMsg());
            springMethod.setErrorStackTrace(springMethodInfo.getLastErrorStackTrace());
            springMethod.setErrorTime(springMethodInfo.getLastErrorTime());
            springMethod.setIsAlarm("n");

            springMethodList.add(springMethod);
        }
        springMethodMapper.batchSaveSpringMethod(springMethodList);
    }

    public Pagination<SpringMethodReportDto> querySpringMethodReport(SpringMethodQuery springMethodQuery) {
        Assert.notNull(springMethodQuery);
        if (springMethodQuery.getHostName() != null && springMethodQuery.getHostName().equals("all")) springMethodQuery.setHostName(null);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,springMethodQuery.getTimeInternal());
        springMethodQuery.setBeginDate(beginDate);
        springMethodQuery.setEndDate(endDate);

        List<SpringMethodReportDto> webUrlList = springMethodMapper.selectByParam(springMethodQuery);
        int total = springMethodMapper.getTotalByParam(springMethodQuery);
        Pagination<SpringMethodReportDto> webUrlPagination = new Pagination<SpringMethodReportDto>(springMethodQuery,webUrlList,total);
        return webUrlPagination;
    }

    public SpringMethodDetailDto buildSpringMethodDetailDto(String hostName, String timeInternal, String applicationCode, String method) {
        Assert.notNull(hostName);
        Assert.notNull(timeInternal);
        Assert.notNull(applicationCode);
        Assert.notNull(method);
        if(hostName.equals("all")) hostName = null;

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        SpringMethodDetailReportDto springMethodDetailReportDto = new SpringMethodDetailReportDto();

        springMethodDetailReportDto.setxAxis(EChartHelper.buildXAxis(timeRangeDto.getFormatTimeList()));
        springMethodDetailReportDto.setyAxis(EChartHelper.buildYAxis());
        springMethodDetailReportDto.setLegend(EChartHelper.buildLegend("访问次数","最大并发"));

        List<Long> totalList = new LinkedList<>();
        List<Long> concurrentMaxList = new LinkedList<Long>();
        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        for (TimeRange timeRange : timeRangeList) {
            SpringMethod springMethod = springMethodMapper.querySpringMethodByParam(applicationCode,hostName,timeRange.getBeginDate(),timeRange.getEndDate(),method);

            if (springMethod != null) {
                totalList.add(springMethod.getCount());
                concurrentMaxList.add((long)springMethod.getConcurrentMax());
            } else {
                totalList.add(0l);
                concurrentMaxList.add(0l);
            }
        }

        List<Map<String,Object>> springMethodSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> countMap = new HashMap<String,Object>();
        countMap.put("name","访问次数");
        countMap.put("type","line");
        countMap.put("stack","总量");
        countMap.put("data",totalList);
        springMethodSeries.add(countMap);

        Map<String,Object> concurrentMaxMap = new HashMap<String,Object>();
        concurrentMaxMap.put("name","最大并发");
        concurrentMaxMap.put("type","line");
        concurrentMaxMap.put("stack","总量");
        concurrentMaxMap.put("data",concurrentMaxList);
        springMethodSeries.add(concurrentMaxMap);
        springMethodDetailReportDto.setSeries(springMethodSeries);

        SpringMethodDetailDto springMethodDetailDto = new SpringMethodDetailDto();
        springMethodDetailDto.setSpringMethodDetailReportDto(springMethodDetailReportDto);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,timeInternal);
        List<SpringMethod> springMethodList = springMethodMapper.querySpringMethodErrorByParam(applicationCode,hostName,beginDate,endDate,method);
        List<SpringMethodErrorDetail> springMethodErrorDetailList = new LinkedList<SpringMethodErrorDetail>();
        if (CollectionUtil.isNotEmpty(springMethodList)) {
            for (SpringMethod springMethod : springMethodList) {
                SpringMethodErrorDetail webUrlErrorDetail = new SpringMethodErrorDetail();
                webUrlErrorDetail.setErrorDateTime(springMethod.getErrorTime());
                webUrlErrorDetail.setErrorMsg(springMethod.getErrorMsg());
                webUrlErrorDetail.setErrorStackTrace(springMethod.getErrorStackTrace());
                springMethodErrorDetailList.add(webUrlErrorDetail);
            }
        }
        springMethodDetailDto.setSpringMethodErrorDetailList(springMethodErrorDetailList);
        return springMethodDetailDto;
    }
}
