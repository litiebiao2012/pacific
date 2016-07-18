package com.pacific.service.impl;

import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.dto.report.HeadMemoryDto;
import com.pacific.domain.dto.report.JVMMemoryReportDto;
import com.pacific.domain.dto.report.NonHeadMemoryDto;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.mapper.JVMMemoryMapper;
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

    public JVMMemoryReportDto queryHeadMemoryDto(String applicationCode, String timeInternal, String clientIp) {
        Assert.notNull(applicationCode);
        Assert.notNull(timeInternal);
        Assert.notNull(clientIp);

        if(clientIp.equals("all")) clientIp = null;
        JVMMemoryReportDto jvmMemoryReportDto = new JVMMemoryReportDto();

        HeadMemoryDto headMemoryDto = new HeadMemoryDto();
        NonHeadMemoryDto nonHeadMemoryDto = new NonHeadMemoryDto();
        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        List<String> timeList = timeRangeDto.getFormatTimeList();
        headMemoryDto.setxAxis(buildXAxis(timeList));
        nonHeadMemoryDto.setxAxis(buildXAxis(timeList));

        headMemoryDto.setyAxis(buildYAxis());
        nonHeadMemoryDto.setyAxis(buildYAxis());

        headMemoryDto.setLegend(buildLegend());
        nonHeadMemoryDto.setLegend(buildLegend());

        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        List<Long> heapMemoryMaxList = new LinkedList<Long>();
        List<Long> heapMemoryUsedList = new LinkedList<Long>();
        List<Long> nonHeapMemoryMaxList = new LinkedList<Long>();
        List<Long> nonHeapMemoryUsedList = new LinkedList<Long>();
        for (TimeRange timeRange : timeRangeList) {
            JVMMemory jvmMemory = jvmMemoryMapper.queryAllJVMMemoryByParam(applicationCode,clientIp,timeRange.getBeginDate(),timeRange.getEndDate());

            if (jvmMemory != null) {
                heapMemoryMaxList.add(jvmMemory.getHeapMemoryMax());
                heapMemoryUsedList.add(jvmMemory.getHeapMemoryUsed());
                nonHeapMemoryMaxList.add(jvmMemory.getNonHeapMemoryMax());
                nonHeapMemoryUsedList.add(jvmMemory.getNonHeapMemoryUsed());
            } else {
                heapMemoryMaxList.add(0l);
                heapMemoryUsedList.add(0l);
                nonHeapMemoryMaxList.add(0l);
                nonHeapMemoryUsedList.add(0l);
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

        jvmMemoryReportDto.setHeadMemoryDto(headMemoryDto);
        jvmMemoryReportDto.setNonHeadMemoryDto(nonHeadMemoryDto);
        return jvmMemoryReportDto;
    }

    public Map<String,Object> buildXAxis(List<String> timeList) {
        Map<String,Object> xAlias = new HashMap<String,Object>();
        xAlias.put("type","category");
        xAlias.put("boundaryGap",false);
        xAlias.put("data",timeList);
        return  xAlias;
    }

    public Map<String,Object> buildYAxis() {
        Map<String,Object> yAlias = new HashMap<String,Object>();
        yAlias.put("type","value");
        return  yAlias;
    }

    public Map<String,Object> buildLegend() {
        Map<String,Object> legendMap = new HashMap<String,Object>();
        List<String> list = new LinkedList<String>();
        list.add("max");
        list.add("used");
        legendMap.put("data",list);
        return  legendMap;
    }
}
