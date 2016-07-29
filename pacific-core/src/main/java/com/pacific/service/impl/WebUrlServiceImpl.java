package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.WebUrlInfo;
import com.pacific.domain.dto.report.*;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.WebUrl;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;
import com.pacific.mapper.WebUrlMapper;
import com.pacific.service.ApplicationService;
import com.pacific.service.EChartHelper;
import com.pacific.service.TimeInternalHelper;
import com.pacific.service.WebUrlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/7/20.
 */
public class WebUrlServiceImpl implements WebUrlService {

    @Resource
    private WebUrlMapper webUrlMapper;

    @Resource
    private ApplicationService applicationService;


    public void saveWebUrlInfo(String appCode,String clientIp,String hostName,List<WebUrlInfo> webUrlInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(hostName);
        Assert.notNull(webUrlInfoList);

        List<WebUrl> webUrlList = new ArrayList<WebUrl>();
        for (WebUrlInfo webUrlInfo : webUrlInfoList) {
            WebUrl webUrl = new WebUrl();
            webUrl.setCreateTime(new Date());
            webUrl.setUpdateTime(new Date());
            webUrl.setApplicationCode(appCode);
            webUrl.setClientIp(clientIp);
            webUrl.setHostName(hostName);
            webUrl.setUrl(webUrlInfo.getUrl());
            webUrl.setCount(webUrlInfo.getCount());
            webUrl.setConcurrentMax(webUrlInfo.getConcurrentMax());
            webUrl.setNanoTotal(webUrlInfo.getNanoTotal());
            webUrl.setNanoMax(webUrlInfo.getNanoMax());
            webUrl.setConcurrentMax(webUrlInfo.getConcurrentMax());

            if (StringUtils.isNotEmpty(webUrlInfo.getLastErrorMsg())) {
                webUrl.setIsError("y");
                webUrl.setErrorMsg(webUrlInfo.getLastErrorMsg());
                webUrl.setErrorTime(webUrlInfo.getLastErrorTime());
            } else {
                webUrl.setIsError("n");
            }
            webUrlList.add(webUrl);
        }
        webUrlMapper.batchSaveWebUrl(webUrlList);
    }


    public Pagination<WebUrlReportDto> queryWebUrlPage(WebUrlQuery webUrlQuery) {
        Assert.notNull(webUrlQuery);
        if (webUrlQuery.getHostName() != null && webUrlQuery.getHostName().equals("all")) webUrlQuery.setHostName(null);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,webUrlQuery.getTimeInternal());
        webUrlQuery.setBeginDate(beginDate);
        webUrlQuery.setEndDate(endDate);

        List<WebUrlReportDto> webUrlList = webUrlMapper.selectByParam(webUrlQuery);
        int total = webUrlMapper.getTotalByParam(webUrlQuery);
        Pagination<WebUrlReportDto> webUrlPagination = new Pagination<WebUrlReportDto>(webUrlQuery,webUrlList,total);
        return webUrlPagination;
    }

    public WebUrlDetailDto buildWebUrlDetailDto(String hostName, String timeInternal, String applicationCode, String url) {
        Assert.notNull(hostName);
        Assert.notNull(timeInternal);
        Assert.notNull(applicationCode);
        Assert.notNull(url);

        if(hostName.equals("all")) hostName = null;

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        WebUrlDetailReportDto webUrlDetailReportDto = new WebUrlDetailReportDto();

        webUrlDetailReportDto.setxAxis(EChartHelper.buildXAxis(timeRangeDto.getFormatTimeList()));
        webUrlDetailReportDto.setyAxis(EChartHelper.buildYAxis());
        webUrlDetailReportDto.setLegend(EChartHelper.buildLegend("访问次数","最大并发"));

        List<Long> totalList = new LinkedList<Long>();
        List<Long> concurrentMaxList = new LinkedList<Long>();
        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        for (TimeRange timeRange : timeRangeList) {
            WebUrl webUrl = webUrlMapper.queryAllWebUrlByParam(applicationCode,hostName,timeRange.getBeginDate(),timeRange.getEndDate(),url);

            if (webUrl != null) {
                totalList.add(webUrl.getCount());
                concurrentMaxList.add((long)webUrl.getConcurrentMax());
            } else {
                totalList.add(0l);
                concurrentMaxList.add(0l);
            }
        }
        List<Map<String,Object>> webUrlDetailSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> countMap = new HashMap<String,Object>();
        countMap.put("name","访问次数");
        countMap.put("type","line");
        countMap.put("stack","总量");
        countMap.put("data",totalList);
        webUrlDetailSeries.add(countMap);

        Map<String,Object> concurrentMaxMap = new HashMap<String,Object>();
        concurrentMaxMap.put("name","最大并发");
        concurrentMaxMap.put("type","line");
        concurrentMaxMap.put("stack","总量");
        concurrentMaxMap.put("data",concurrentMaxList);
        webUrlDetailSeries.add(concurrentMaxMap);
        webUrlDetailReportDto.setSeries(webUrlDetailSeries);

        WebUrlDetailDto webUrlDetailDto = new WebUrlDetailDto();
        webUrlDetailDto.setWebUrlDetailReportDto(webUrlDetailReportDto);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,timeInternal);
        List<WebUrl> webUrlList = webUrlMapper.queryWebUrlErrorByParam(applicationCode,hostName,beginDate,endDate,url);
        List<WebUrlErrorDetail> webUrlErrorDetailList = new LinkedList<WebUrlErrorDetail>();
        if (CollectionUtil.isNotEmpty(webUrlList)) {
            for (WebUrl webUrl : webUrlList) {
                WebUrlErrorDetail webUrlErrorDetail = new WebUrlErrorDetail();
                webUrlErrorDetail.setErrorDateTime(webUrl.getErrorTime());
                webUrlErrorDetail.setErrorMsg(webUrl.getErrorMsg());
                webUrlErrorDetailList.add(webUrlErrorDetail);
            }
        }
        webUrlDetailDto.setWebUrlErrorDetail(webUrlErrorDetailList);
        return webUrlDetailDto;
    }

    public SevenDayWebUrlReport querySevenDayWebUrlReport(String isError) {
        Assert.notNull(isError);
        SevenDayWebUrlReport sevenDayWebUrlReport = new SevenDayWebUrlReport();
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());

        List<String> appNameList = new LinkedList<String>();
        List<SevenDayWebUrlSeries> appErrorLogSeriesList = new LinkedList<SevenDayWebUrlSeries>();
        List<Date> dateList = TimeInternalHelper.buildSevenDayDateList();
        if (CollectionUtil.isNotEmpty(applicationList)) {
            for (Application application : applicationList) {
                appNameList.add(application.getApplicationName());

                SevenDayWebUrlSeries sevenDayWebUrlSeries = new SevenDayWebUrlSeries();
                sevenDayWebUrlSeries.setName(application.getApplicationName());
                sevenDayWebUrlSeries.setType("line");
                sevenDayWebUrlSeries.setStack("总量");
                Map<String,Object> areaStyleMap = new HashMap<String,Object>();
                areaStyleMap.put("normal",new HashMap<>());
                sevenDayWebUrlSeries.setAreaStyle(areaStyleMap);

                List<Integer> data = new LinkedList<Integer>();
                for (Date date : dateList) {
                    Date beginDate = DateUtil.getBeginTimeOfDay(date).toDate();
                    Date endDate = DateUtil.getEndTimeOfDay(date).toDate();
                    int total = webUrlMapper.queryTotalByParam(application.getApplicationCode(),beginDate,endDate,isError);
                    data.add(total);
                }
                sevenDayWebUrlSeries.setData(data);
                appErrorLogSeriesList.add(sevenDayWebUrlSeries);
            }
        }
        Map<String,List<String>> legendMap = new HashMap<String,List<String>>();
        legendMap.put("data",appNameList);
        sevenDayWebUrlReport.setLegend(legendMap);

        Map<String,Object> toolboxMap = new HashMap<String,Object>();
        Map<String,Object> saveImageMap = new HashMap<String,Object>();
        toolboxMap.put("feature",saveImageMap);
        sevenDayWebUrlReport.setToolbox(toolboxMap);


        List<Map<String,Object>> xAxis = new ArrayList<Map<String,Object>>();
        Map<String,Object> xAxisMap = new HashMap<String,Object>();
        xAxisMap.put("type","category");
        xAxisMap.put("boundaryGap",false);
        List<String> xAxisDataList = new LinkedList<String>();

        for (Date date : dateList) {
            xAxisDataList.add(DateUtil.format(date,DateUtil.DATE_PATTERN));
        }
        xAxisMap.put("data",xAxisDataList);
        xAxis.add(xAxisMap);
        sevenDayWebUrlReport.setxAxis(xAxis);

        List<Map<String,Object>> yAxis = new ArrayList<Map<String,Object>>();
        Map<String,Object> yAxisMap = new HashMap<String,Object>();
        yAxisMap.put("type","value");
        yAxis.add(yAxisMap);
        sevenDayWebUrlReport.setyAxis(yAxis);

        sevenDayWebUrlReport.setSeries(appErrorLogSeriesList);
        return sevenDayWebUrlReport;


    }
}
