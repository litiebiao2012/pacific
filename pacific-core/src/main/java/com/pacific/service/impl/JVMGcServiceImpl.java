package com.pacific.service.impl;

import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.JVMGcDto;
import com.pacific.domain.dto.report.GcCountDto;
import com.pacific.domain.dto.report.GcTimeDto;
import com.pacific.domain.dto.report.JVMGcReportDto;
import com.pacific.domain.entity.JVMGc;
import com.pacific.mapper.JVMGcMapper;
import com.pacific.service.EChartHelper;
import com.pacific.service.JVMGcService;
import com.pacific.service.TimeInternalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMGcServiceImpl implements JVMGcService {

    public static final Logger logger = LoggerFactory.getLogger(JVMGcServiceImpl.class);

    @Resource
    private JVMGcMapper jvmGcMapper;

    @Override
    public void saveJVMGc(JVMGcDto jvmGcDto) {
        JVMGc jvmGc = new JVMGc();
        try {
            jvmGc.setYoungGcCollectionCount(jvmGcDto.getYoungGCCollectionCount());
            jvmGc.setYoungGcCollectionTime(jvmGcDto.getYoungGCCollectionTime());
            jvmGc.setFullGcCollectionCount(jvmGcDto.getFullGCCollectionCount());
            jvmGc.setFullGcCollectionTime(jvmGcDto.getFullGCCollectionTime());
            jvmGc.setClientIp(jvmGcDto.getClientIp());
            jvmGc.setHostName(jvmGcDto.getHostName());
            jvmGc.setApplicationCode(jvmGcDto.getAppCode());
            jvmGc.setCreateTime(new Date());
            jvmGc.setUpdateTime(new Date());
            jvmGcMapper.insertSelective(jvmGc);
        } catch (Exception e) {
            logger.error("saveJVMGc error!, e : {}",e);
        }
    }

    public JVMGcReportDto queryJVMGcReportDto(String applicationCode, String timeInternal, String clientIp) {
        Assert.notNull(applicationCode);
        Assert.notNull(timeInternal);
        Assert.notNull(clientIp);

        if(clientIp.equals("all")) clientIp = null;

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        List<String> timeList = timeRangeDto.getFormatTimeList();
        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();

        JVMGcReportDto jvmGcReportDto = new JVMGcReportDto();

        GcCountDto gcCountDto = new GcCountDto();
        GcTimeDto gcTimeDto = new GcTimeDto();

        gcCountDto.setxAxis(EChartHelper.buildXAxis(timeList));
        gcTimeDto.setxAxis(EChartHelper.buildXAxis(timeList));

        gcCountDto.setyAxis(EChartHelper.buildYAxis());
        gcTimeDto.setyAxis(EChartHelper.buildYAxis());

        gcCountDto.setLegend(EChartHelper.buildLegend("fullGC","youngGC"));
        gcTimeDto.setLegend(EChartHelper.buildLegend("fullGC(ms)","youngGC(ms)"));

        List<Long> fullGcCountList = new LinkedList<Long>();
        List<Long> youngGcCountList = new LinkedList<Long>();

        List<Long> fullGcTimeList = new LinkedList<Long>();
        List<Long> youngGcTimeList = new LinkedList<Long>();
        for (TimeRange timeRange : timeRangeList) {
            JVMGc jvmGc = jvmGcMapper.queryAllJVMGcByParam(applicationCode,clientIp,timeRange.getBeginDate(),timeRange.getEndDate());

            if (jvmGc != null) {
                fullGcCountList.add(jvmGc.getFullGcCollectionCount());
                youngGcCountList.add(jvmGc.getYoungGcCollectionCount());
                fullGcTimeList.add(jvmGc.getFullGcCollectionTime());
                youngGcTimeList.add(jvmGc.getYoungGcCollectionTime());
            } else {
                fullGcCountList.add(0l);
                youngGcCountList.add(0l);
                fullGcTimeList.add(0l);
                youngGcTimeList.add(0l);
            }
        }

        List<Map<String,Object>> gcCountSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> fullGcCountMap = new HashMap<String,Object>();
        fullGcCountMap.put("name","fullGC");
        fullGcCountMap.put("type","line");
        fullGcCountMap.put("stack","总量");
        fullGcCountMap.put("data",fullGcCountList);
        gcCountSeries.add(fullGcCountMap);

        Map<String,Object> youngGcCountMap = new HashMap<String,Object>();
        youngGcCountMap.put("name","youngGC");
        youngGcCountMap.put("type","line");
        youngGcCountMap.put("stack","总量");
        youngGcCountMap.put("data",youngGcCountList);
        gcCountSeries.add(youngGcCountMap);
        gcCountDto.setSeries(gcCountSeries);


        List<Map<String,Object>> gcTimeSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> fullGcTimeMap = new HashMap<String,Object>();
        fullGcTimeMap.put("name","fullGC(ms)");
        fullGcTimeMap.put("type","line");
        fullGcTimeMap.put("stack","总量");
        fullGcTimeMap.put("data",fullGcTimeList);
        gcTimeSeries.add(fullGcTimeMap);

        Map<String,Object> youngGcTimeMap = new HashMap<String,Object>();
        youngGcTimeMap.put("name","youngGC(ms)");
        youngGcTimeMap.put("type","line");
        youngGcTimeMap.put("stack","总量");
        youngGcTimeMap.put("data",youngGcTimeList);
        gcTimeSeries.add(youngGcTimeMap);
        gcTimeDto.setSeries(gcTimeSeries);

        jvmGcReportDto.setGcCountDto(gcCountDto);
        jvmGcReportDto.setGcTimeDto(gcTimeDto);
        return jvmGcReportDto;
    }
}
