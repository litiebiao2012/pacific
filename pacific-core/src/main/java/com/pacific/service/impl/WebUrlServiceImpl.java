package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.WebUrlInfo;
import com.pacific.domain.dto.report.WebUrlDetailDto;
import com.pacific.domain.dto.report.WebUrlDetailReportDto;
import com.pacific.domain.dto.report.WebUrlErrorDetail;
import com.pacific.domain.dto.report.WebUrlReportDto;
import com.pacific.domain.entity.WebUrl;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;
import com.pacific.mapper.WebUrlMapper;
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


    public void saveWebUrlInfo(String appCode,String clientIp,List<WebUrlInfo> webUrlInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(webUrlInfoList);

        List<WebUrl> webUrlList = new ArrayList<WebUrl>();
        for (WebUrlInfo webUrlInfo : webUrlInfoList) {
            WebUrl webUrl = new WebUrl();
            webUrl.setCreateTime(new Date());
            webUrl.setUpdateTime(new Date());
            webUrl.setApplicationCode(appCode);
            webUrl.setClientIp(clientIp);
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
        if (webUrlQuery.getClientIp() != null && webUrlQuery.getClientIp().equals("all")) webUrlQuery.setClientIp(null);

        Date endDate = new Date();
        Date beginDate = TimeInternalHelper.getBeginDate(endDate,webUrlQuery.getTimeInternal());
        webUrlQuery.setBeginDate(beginDate);
        webUrlQuery.setEndDate(endDate);

        List<WebUrlReportDto> webUrlList = webUrlMapper.selectByParam(webUrlQuery);
        int total = webUrlMapper.getTotalByParam(webUrlQuery);
        Pagination<WebUrlReportDto> webUrlPagination = new Pagination<WebUrlReportDto>(webUrlQuery,webUrlList,total);
        return webUrlPagination;
    }

    public WebUrlDetailDto buildWebUrlDetailDto(String clientIp, String timeInternal, String applicationCode, String url) {
        Assert.notNull(clientIp);
        Assert.notNull(timeInternal);
        Assert.notNull(applicationCode);
        Assert.notNull(url);

        if(clientIp.equals("all")) clientIp = null;

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        WebUrlDetailReportDto webUrlDetailReportDto = new WebUrlDetailReportDto();

        webUrlDetailReportDto.setxAxis(EChartHelper.buildXAxis(timeRangeDto.getFormatTimeList()));
        webUrlDetailReportDto.setyAxis(EChartHelper.buildYAxis());
        webUrlDetailReportDto.setLegend(EChartHelper.buildLegend("访问次数","最大并发"));

        List<Long> totalList = new LinkedList<Long>();
        List<Long> concurrentMaxList = new LinkedList<Long>();
        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        for (TimeRange timeRange : timeRangeList) {
            WebUrl webUrl = webUrlMapper.queryAllWebUrlByParam(applicationCode,clientIp,timeRange.getBeginDate(),timeRange.getEndDate(),url);

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
        List<WebUrl> webUrlList = webUrlMapper.queryWebUrlErrorByParam(applicationCode,clientIp,beginDate,endDate,url);
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
}
