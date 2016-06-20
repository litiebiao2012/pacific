package com.pacific.service;

import com.pacific.domain.dto.AlarmLogDto;
import com.pacific.domain.dto.AllAppErrorLogReportDto;
import com.pacific.domain.dto.AllAppErrorLogSevenDayReportDto;
import com.pacific.domain.query.AlarmLogQuery;

import java.util.List;

/**
 * Created by Fe on 16/6/18.
 */
public interface AlarmLogService {

    public Long getDayAlarmLogCount();

    public List<AlarmLogDto> queryDayAlarmLog(AlarmLogQuery alarmLogQuery);

    public AllAppErrorLogSevenDayReportDto queryAllAppErrorLogSevenDayReport();


    public AllAppErrorLogReportDto queryAllAppErrorLogReport();
}
