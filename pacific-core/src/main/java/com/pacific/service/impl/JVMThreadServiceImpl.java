package com.pacific.service.impl;

import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.dto.jvm.JVMThreadDto;
import com.pacific.domain.dto.report.JVMThreadReportDto;
import com.pacific.domain.dto.report.ThreadCpuRateReportDto;
import com.pacific.domain.dto.report.ThreadReportDto;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.domain.entity.JVMThread;
import com.pacific.mapper.JVMThreadMapper;
import com.pacific.service.EChartHelper;
import com.pacific.service.JVMThreadService;
import com.pacific.service.TimeInternalHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMThreadServiceImpl implements JVMThreadService {

    public static final Logger logger = LoggerFactory.getLogger(JVMMemoryServiceImpl.class);

    @Resource
    private JVMThreadMapper jvmThreadMapper;

    @Override
    public void saveJVMThread(JVMThreadDto jvmThreadDto) {
        JVMThread jvmThread = new JVMThread();
        try {
            BeanUtils.copyProperties(jvmThread,jvmThreadDto);
            jvmThread.setApplicationCode(jvmThreadDto.getAppCode());
            jvmThread.setCreateTime(new Date());
            jvmThread.setUpdateTime(new Date());
            jvmThreadMapper.insertSelective(jvmThread);
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }


    public JVMThreadReportDto queryThreadDto(String applicationCode, String timeInternal, String clientIp) {
        Assert.notNull(applicationCode);
        Assert.notNull(timeInternal);
        Assert.notNull(clientIp);

        if(clientIp.equals("all")) clientIp = null;
        JVMThreadReportDto jvmThreadReportDto = new JVMThreadReportDto();
        ThreadReportDto threadReportDto = new ThreadReportDto();
        ThreadCpuRateReportDto threadCpuRateReportDto = new ThreadCpuRateReportDto();
        TimeRangeDto timeRangeDto = TimeInternalHelper.getTimeRangeByInternal(timeInternal);
        List<String> timeList = timeRangeDto.getFormatTimeList();

        threadReportDto.setxAxis(EChartHelper.buildXAxis(timeList));
        threadReportDto.setyAxis(EChartHelper.buildYAxis());
        threadReportDto.setLegend(EChartHelper.buildLegend("总线程","daemon线程","死锁线程"));

        threadCpuRateReportDto.setxAxis(EChartHelper.buildXAxis(timeList));
        threadCpuRateReportDto.setyAxis(EChartHelper.buildYAxis());
        threadCpuRateReportDto.setLegend(EChartHelper.buildLegend("百分比"));


        List<TimeRange> timeRangeList = timeRangeDto.getTimeRangeDtoList();
        List<Integer> threadCountList = new LinkedList<Integer>();
        List<Integer> daemonThreadCountList = new LinkedList<Integer>();
        List<Integer> deadLockThreadCountList = new LinkedList<Integer>();
        List<BigDecimal> processCpuTimeRateList = new LinkedList<BigDecimal>();
        for (TimeRange timeRange : timeRangeList) {
            JVMThread jvmThread = jvmThreadMapper.queryAllJVMThreadByParam(applicationCode,clientIp,timeRange.getBeginDate(),timeRange.getEndDate());
            if (jvmThread != null) {
                threadCountList.add(jvmThread.getThreadCount());
                daemonThreadCountList.add(jvmThread.getDaemonThreadCount());
                deadLockThreadCountList.add(jvmThread.getDeadLockedThreadCount());
                processCpuTimeRateList.add(jvmThread.getProcessCpuTimeRate());
            } else {
                threadCountList.add(0);
                daemonThreadCountList.add(0);
                deadLockThreadCountList.add(0);
                processCpuTimeRateList.add(new BigDecimal(0));
            }
        }

        List<Map<String,Object>> threadSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> threadCountMap = new HashMap<String,Object>();
        threadCountMap.put("name","总线程");
        threadCountMap.put("type","line");
        threadCountMap.put("stack","总量");
        threadCountMap.put("data",threadCountList);
        threadSeries.add(threadCountMap);

        Map<String,Object> daemonThreadCountMap = new HashMap<String,Object>();
        daemonThreadCountMap.put("name","daemon线程");
        daemonThreadCountMap.put("type","line");
        daemonThreadCountMap.put("stack","总量");
        daemonThreadCountMap.put("data",daemonThreadCountList);
        threadSeries.add(daemonThreadCountMap);

        Map<String,Object> deadLockThreadCountMap = new HashMap<String,Object>();
        deadLockThreadCountMap.put("name","死锁线程");
        deadLockThreadCountMap.put("type","line");
        deadLockThreadCountMap.put("stack","总量");
        deadLockThreadCountMap.put("data",deadLockThreadCountList);
        threadSeries.add(deadLockThreadCountMap);
        threadReportDto.setSeries(threadSeries);


        List<Map<String,Object>> threadCpuRateSeries = new LinkedList<Map<String,Object>>();
        Map<String,Object> threadCpuRateMap = new HashMap<String,Object>();
        threadCpuRateMap.put("name","百分比");
        threadCpuRateMap.put("type","line");
        threadCpuRateMap.put("stack","总量");
        threadCpuRateMap.put("data",processCpuTimeRateList);
        threadCpuRateSeries.add(threadCpuRateMap);
        threadCpuRateReportDto.setSeries(threadCpuRateSeries);

        jvmThreadReportDto.setThreadReportDto(threadReportDto);
        jvmThreadReportDto.setThreadCpuRateReportDto(threadCpuRateReportDto);

        return jvmThreadReportDto;
    }
}
