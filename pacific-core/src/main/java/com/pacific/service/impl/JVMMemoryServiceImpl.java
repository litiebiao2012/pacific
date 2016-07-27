package com.pacific.service.impl;

import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.dto.report.HeadMemoryDto;
import com.pacific.domain.dto.report.JVMMemoryDetailDto;
import com.pacific.domain.dto.report.JVMMemoryReportDto;
import com.pacific.domain.dto.report.NonHeadMemoryDto;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.mapper.JVMMemoryMapper;
import com.pacific.service.EChartHelper;
import com.pacific.service.JVMMemoryService;
import com.pacific.service.TimeInternalHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMMemoryServiceImpl implements JVMMemoryService{
    public static final Logger logger = LoggerFactory.getLogger(JVMMemoryServiceImpl.class);

    @Resource
    private JVMMemoryMapper jvmMemoryMapper;

    @Override
    public void saveJVMMemory(JVMMemoryDto jvmMemoryDto) {
        JVMMemory jvmMemory = new JVMMemory();
        try {
            BeanUtils.copyProperties(jvmMemory,jvmMemoryDto);
            jvmMemory.setApplicationCode(jvmMemoryDto.getAppCode());
            jvmMemory.setCreateTime(new Date());
            jvmMemory.setUpdateTime(new Date());
            jvmMemoryMapper.insertSelective(jvmMemory);
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }

    public JVMMemoryReportDto queryJVMMemoryDto(String applicationCode, String timeInternal, String hostName) {
        Assert.notNull(applicationCode);
        Assert.notNull(timeInternal);
        Assert.notNull(hostName);

        if(hostName.equals("all")) hostName = null;
        JVMMemoryReportDto jvmMemoryReportDto = new JVMMemoryReportDto();

        HeadMemoryDto headMemoryDto = new HeadMemoryDto();
        NonHeadMemoryDto nonHeadMemoryDto = new NonHeadMemoryDto();
        JVMMemoryDetailDto jvmMemoryDetailDto = new JVMMemoryDetailDto();

        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        List<String> timeList = timeRangeDto.getFormatTimeList();

        headMemoryDto.setxAxis(EChartHelper.buildXAxis(timeList));
        nonHeadMemoryDto.setxAxis(EChartHelper.buildXAxis(timeList));
        jvmMemoryDetailDto.setxAxis(EChartHelper.buildXAxis(timeList));

        headMemoryDto.setyAxis(EChartHelper.buildYAxis());
        nonHeadMemoryDto.setyAxis(EChartHelper.buildYAxis());
        jvmMemoryDetailDto.setyAxis(EChartHelper.buildYAxis());

        headMemoryDto.setLegend(EChartHelper.buildLegend("max","used"));
        nonHeadMemoryDto.setLegend(EChartHelper.buildLegend("max","used"));
        jvmMemoryDetailDto.setLegend(EChartHelper.buildLegend("PermGen max","PermGen used","OldGen max",
                "OldGen used","EdenSpace max","EdenSpace used",
                "Survivor max","Survivor used"));

        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        List<Long> heapMemoryMaxList = new LinkedList<Long>();
        List<Long> heapMemoryUsedList = new LinkedList<Long>();
        List<Long> nonHeapMemoryMaxList = new LinkedList<Long>();
        List<Long> nonHeapMemoryUsedList = new LinkedList<Long>();

        List<Long> permGenMaxList = new LinkedList<Long>();
        List<Long> permGenUsedList = new LinkedList<Long>();
        List<Long> oldGenMaxList = new LinkedList<Long>();
        List<Long> oldGenUsedList = new LinkedList<Long>();
        List<Long> edenSpaceMaxList = new LinkedList<Long>();
        List<Long> edenSpaceUsedList = new LinkedList<Long>();
        List<Long> survivorMaxList = new LinkedList<Long>();
        List<Long> survivorUsedList = new LinkedList<Long>();
        for (TimeRange timeRange : timeRangeList) {
            JVMMemory jvmMemory = jvmMemoryMapper.queryAllJVMMemoryByParam(applicationCode,hostName,timeRange.getBeginDate(),timeRange.getEndDate());

            if (jvmMemory != null) {
                heapMemoryMaxList.add(jvmMemory.getHeapMemoryMax());
                heapMemoryUsedList.add(jvmMemory.getHeapMemoryUsed());
                nonHeapMemoryMaxList.add(jvmMemory.getNonHeapMemoryMax());
                nonHeapMemoryUsedList.add(jvmMemory.getNonHeapMemoryUsed());


                permGenMaxList.add(jvmMemory.getPermGenMax());
                permGenUsedList.add(jvmMemory.getPermGenUsed());
                oldGenMaxList.add(jvmMemory.getOldGenMax());
                oldGenUsedList.add(jvmMemory.getOldGenUsed());
                edenSpaceMaxList.add(jvmMemory.getEdenSpaceMax());
                edenSpaceUsedList.add(jvmMemory.getEdenSpaceUsed());
                survivorMaxList.add(jvmMemory.getSurvivorMax());
                survivorUsedList.add(jvmMemory.getSurvivorUsed());
            } else {
                heapMemoryMaxList.add(0l);
                heapMemoryUsedList.add(0l);
                nonHeapMemoryMaxList.add(0l);
                nonHeapMemoryUsedList.add(0l);

                permGenMaxList.add(0l);
                permGenUsedList.add(0l);
                oldGenMaxList.add(0l);
                oldGenUsedList.add(0l);
                edenSpaceMaxList.add(0l);
                edenSpaceUsedList.add(0l);
                survivorMaxList.add(0l);
                survivorUsedList.add(0l);
            }
        }

        List<Map<String,Object>> headMemorySeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> headMaxMemoryMap = new HashMap<String,Object>();
        headMaxMemoryMap.put("name","max");
        headMaxMemoryMap.put("type","line");
        headMaxMemoryMap.put("stack","总量");
        headMaxMemoryMap.put("data",heapMemoryMaxList);
        headMemorySeries.add(headMaxMemoryMap);

        Map<String,Object> headUsedMemoryMap = new HashMap<String,Object>();
        headUsedMemoryMap.put("name","used");
        headUsedMemoryMap.put("type","line");
        headUsedMemoryMap.put("stack","总量");
        headUsedMemoryMap.put("data",heapMemoryUsedList);
        headMemorySeries.add(headUsedMemoryMap);
        headMemoryDto.setSeries(headMemorySeries);

        List<Map<String,Object>> nonHeadMemorySeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> nonHeadMaxMemoryMap = new HashMap<String,Object>();
        nonHeadMaxMemoryMap.put("name","max");
        nonHeadMaxMemoryMap.put("type","line");
        nonHeadMaxMemoryMap.put("stack","总量");
        nonHeadMaxMemoryMap.put("data",nonHeapMemoryMaxList);
        nonHeadMemorySeries.add(nonHeadMaxMemoryMap);

        Map<String,Object> nonHeadUsedMemoryMap = new HashMap<String,Object>();
        nonHeadUsedMemoryMap.put("name","used");
        nonHeadUsedMemoryMap.put("type","line");
        nonHeadUsedMemoryMap.put("stack","总量");
        nonHeadUsedMemoryMap.put("data",nonHeapMemoryUsedList);
        nonHeadMemorySeries.add(nonHeadUsedMemoryMap);
        nonHeadMemoryDto.setSeries(nonHeadMemorySeries);



        List<Map<String,Object>> jvmDetailMemorySeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> permGenMaxMap = new HashMap<String,Object>();
        permGenMaxMap.put("name","PermGen max");
        permGenMaxMap.put("type","line");
        permGenMaxMap.put("stack","总量");
        permGenMaxMap.put("data",permGenMaxList);
        jvmDetailMemorySeries.add(permGenMaxMap);

        Map<String,Object> permGenUsedMap = new HashMap<String,Object>();
        permGenUsedMap.put("name","PermGen used");
        permGenUsedMap.put("type","line");
        permGenUsedMap.put("stack","总量");
        permGenUsedMap.put("data",permGenUsedList);
        jvmDetailMemorySeries.add(permGenUsedMap);

        Map<String,Object> oldGenMaxMap = new HashMap<String,Object>();
        oldGenMaxMap.put("name","OldGen max");
        oldGenMaxMap.put("type","line");
        oldGenMaxMap.put("stack","总量");
        oldGenMaxMap.put("data",oldGenMaxList);
        jvmDetailMemorySeries.add(oldGenMaxMap);

        Map<String,Object> oldGenUsedMap = new HashMap<String,Object>();
        oldGenUsedMap.put("name","OldGen used");
        oldGenUsedMap.put("type","line");
        oldGenUsedMap.put("stack","总量");
        oldGenUsedMap.put("data",oldGenUsedList);
        jvmDetailMemorySeries.add(oldGenUsedMap);

        Map<String,Object> edenSpaceMaxMap = new HashMap<String,Object>();
        edenSpaceMaxMap.put("name","EdenSpace max");
        edenSpaceMaxMap.put("type","line");
        edenSpaceMaxMap.put("stack","总量");
        edenSpaceMaxMap.put("data",edenSpaceMaxList);
        jvmDetailMemorySeries.add(edenSpaceMaxMap);

        Map<String,Object> enenSpaceUsedMap = new HashMap<String,Object>();
        enenSpaceUsedMap.put("name","EdenSpace used");
        enenSpaceUsedMap.put("type","line");
        enenSpaceUsedMap.put("stack","总量");
        enenSpaceUsedMap.put("data",edenSpaceUsedList);
        jvmDetailMemorySeries.add(enenSpaceUsedMap);

        Map<String,Object> survivorMaxMap = new HashMap<String,Object>();
        survivorMaxMap.put("name","Survivor max");
        survivorMaxMap.put("type","line");
        survivorMaxMap.put("stack","总量");
        survivorMaxMap.put("data",survivorMaxList);
        jvmDetailMemorySeries.add(survivorMaxMap);

        Map<String,Object> survivorUsedMap = new HashMap<String,Object>();
        survivorUsedMap.put("name","Survivor used");
        survivorUsedMap.put("type","line");
        survivorUsedMap.put("stack","总量");
        survivorUsedMap.put("data",survivorUsedList);
        jvmDetailMemorySeries.add(survivorUsedMap);
        jvmMemoryDetailDto.setSeries(jvmDetailMemorySeries);

        jvmMemoryReportDto.setHeadMemoryDto(headMemoryDto);
        jvmMemoryReportDto.setNonHeadMemoryDto(nonHeadMemoryDto);
        jvmMemoryReportDto.setJvmMemoryDetailDto(jvmMemoryDetailDto);
        return jvmMemoryReportDto;
    }


}
