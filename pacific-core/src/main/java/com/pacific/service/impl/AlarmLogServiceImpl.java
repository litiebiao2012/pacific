package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.dto.*;
import com.pacific.domain.entity.Application;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.mapper.AlarmLogMapper;
import com.pacific.service.AlarmLogService;
import com.pacific.service.ApplicationService;
import com.pacific.service.ElasticSearchHelper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Fe on 16/6/18.
 */
public class AlarmLogServiceImpl implements AlarmLogService {

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Resource
    private ElasticSearchHelper elasticSearchHelper;

    @Resource
    private ApplicationService applicationService;

    @Override
    public Long getDayAlarmLogCount() {
        AlarmLogQuery alarmLogQuery = new AlarmLogQuery();
        Date date = new Date();
        alarmLogQuery.setBeginDate(DateUtil.getBeginTimeOfDay(date).toDate());
        alarmLogQuery.setEndDate(DateUtil.getEndTimeOfDay(date).toDate());
        return alarmLogMapper.queryTotalAlarmLog(alarmLogQuery);
    }

    public List<AlarmLogDto> queryDayAlarmLog(AlarmLogQuery alarmLogQuery) {
        Assert.notNull(alarmLogQuery);
        return alarmLogMapper.queryAlarmLog(alarmLogQuery);
    }


    public Pagination<AlarmLogDto> queryAllAlarmLogByPage(AlarmLogQuery alarmLogQuery) {
        Assert.notNull(alarmLogQuery);
        List<AlarmLogDto> alarmLogDtoList = alarmLogMapper.queryAlarmLog(alarmLogQuery);
        long total = alarmLogMapper.queryTotalAlarmLog(alarmLogQuery);
        return new Pagination<AlarmLogDto>(alarmLogQuery,alarmLogDtoList,(int)total);
    }


    public AllAppErrorLogSevenDayReportDto queryAllAppErrorLogSevenDayReport() {

        AllAppErrorLogSevenDayReportDto allAppErrorLogSevenDayReportDto = new AllAppErrorLogSevenDayReportDto();

        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        if (CollectionUtil.isNotEmpty(applicationList)) {
            Map<String,String> titleMap = new HashMap<String,String>();
            titleMap.put("text","");
            allAppErrorLogSevenDayReportDto.setTitle(titleMap);

            Map<String,String> tooltipMap = new HashMap<String,String>();
            tooltipMap.put("trigger","axis");
            allAppErrorLogSevenDayReportDto.setTooltip(tooltipMap);

            List<String> appNameList = new LinkedList<String>();

            List<AppErrorLogSeries> appErrorLogSeriesList = new LinkedList<AppErrorLogSeries>();
            List<Date> dateList = buildSevenDayDateList();
            for (Application application : applicationList) {
                appNameList.add(application.getApplicationName());

                AppErrorLogSeries appErrorLogSeries = new AppErrorLogSeries();
                appErrorLogSeries.setName(application.getApplicationName());
                appErrorLogSeries.setType("line");
                appErrorLogSeries.setStack("总量");
                Map<String,Object> areaStyleMap = new HashMap<String,Object>();
                areaStyleMap.put("normal",new HashMap<>());
                appErrorLogSeries.setAreaStyle(areaStyleMap);

                List<Long> data = new LinkedList<Long>();
                for (Date date : dateList) {
                    LoggerQuery loggerQuery = new LoggerQuery();
                    Date beginDate = DateUtil.getBeginTimeOfDay(date).toDate();
                    Date endDate = DateUtil.getEndTimeOfDay(date).toDate();
                    loggerQuery.setBeginDate(beginDate);
                    loggerQuery.setEndDate(endDate);
                    loggerQuery.setLevel("error");

                    Long total = elasticSearchHelper.queryTotalLog(application.getApplicationCode(),loggerQuery);
                    data.add(total);
                }
                appErrorLogSeries.setData(data);
                appErrorLogSeriesList.add(appErrorLogSeries);
            }
            Map<String,List<String>> legendMap = new HashMap<String,List<String>>();
            legendMap.put("data",appNameList);
            allAppErrorLogSevenDayReportDto.setLegend(legendMap);

            Map<String,Object> toolboxMap = new HashMap<String,Object>();
            Map<String,Object> saveImageMap = new HashMap<String,Object>();
            toolboxMap.put("feature",saveImageMap);
            allAppErrorLogSevenDayReportDto.setToolbox(toolboxMap);

            Map<String,Object> gridMap = new HashMap<String,Object>();
            gridMap.put("left","3%");
            gridMap.put("right","4%");
            gridMap.put("bottom","3%");
            gridMap.put("containLabel",true);
            allAppErrorLogSevenDayReportDto.setGrid(gridMap);


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
            allAppErrorLogSevenDayReportDto.setxAxis(xAxis);

            List<Map<String,Object>> yAxis = new ArrayList<Map<String,Object>>();
            Map<String,Object> yAxisMap = new HashMap<String,Object>();
            yAxisMap.put("type","value");
            yAxis.add(yAxisMap);
            allAppErrorLogSevenDayReportDto.setyAxis(yAxis);

            allAppErrorLogSevenDayReportDto.setSeries(appErrorLogSeriesList);
        }
        return allAppErrorLogSevenDayReportDto;
    }


    public AllAppErrorLogDayHourReportDto queryAllAppErrorLogDayHourReport() {

        return null;
    }


    public AllAppErrorLogReportDto queryAllAppErrorLogReport() {

        AllAppErrorLogReportDto allAppErrorLogReportDto = new AllAppErrorLogReportDto();
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        if (CollectionUtil.isNotEmpty(applicationList)) {
            Map<String,String> titleMap = new HashMap<String,String>();
            titleMap.put("text","错误日志总数汇总");
            titleMap.put("subtext","数据汇总");
            titleMap.put("x","center");
            allAppErrorLogReportDto.setTitle(titleMap);

            Map<String,String> tooltipMap = new HashMap<String,String>();
            tooltipMap.put("trigger","item");
            tooltipMap.put("formatter","{a} <br/>{b} : {c} ({d}%)");
            allAppErrorLogReportDto.setTooltip(tooltipMap);


            Map<String,Object> legendMap = new HashMap<String,Object>();
            legendMap.put("orient","vertical");
            legendMap.put("left","left");


            List<String> appNameList = new LinkedList<String>();

            List<Map<String,Object>> seriesList = new ArrayList<Map<String,Object>>();

            Map<String,Object> seriesMap = new HashMap<String,Object>();
            List<Map<String,Object>> seriesDataList = new LinkedList<Map<String,Object>>();
            LoggerQuery loggerQuery = new LoggerQuery();
            loggerQuery.setLevel("error");
            for (Application app : applicationList) {
                appNameList.add(app.getApplicationName());
                Map<String,Object> dataMap = new HashMap<String,Object>();

                Long total = elasticSearchHelper.queryTotalLog(app.getApplicationCode(),loggerQuery);
                dataMap.put("value",total);
                dataMap.put("name",app.getApplicationName());
                seriesDataList.add(dataMap);
            }
            legendMap.put("data",appNameList);
            allAppErrorLogReportDto.setLegend(legendMap);

            seriesMap.put("name","错误日志数量汇总");
            seriesMap.put("type","pie");
            seriesMap.put("radius","55%");
            seriesMap.put("center",Arrays.asList("50%","60%"));
            seriesMap.put("data",seriesDataList);

            Map<String,Object> itemStyleMap = new HashMap<String,Object>();
            Map<String,Object> emphasisMap = new HashMap<String,Object>();
            emphasisMap.put("shadowBlur",10);
            emphasisMap.put("shadowOffsetX",0);
            emphasisMap.put("shadowColor","rgba(0, 0, 0, 0.5)");
            itemStyleMap.put("emphasis",itemStyleMap);
            seriesMap.put("itemStyle",itemStyleMap);
            seriesList.add(seriesMap);
            allAppErrorLogReportDto.setSeries(seriesList);
        }
        return allAppErrorLogReportDto;
    }

    private List<Date> buildSevenDayDateList() {
        List<Date> dateList = new LinkedList<Date>();
        dateList.add(new Date());
        for (int i = -1; i >=-6; i--) {
            dateList.add(DateUtil.offsiteDay(new Date(),i).toDate());
        }
        CollectionUtil.sort(dateList, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                if (o1.getTime() >  o2.getTime()) {
                    return 1;
                } else if (o1.getTime() < o2.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return dateList;
    }

}
