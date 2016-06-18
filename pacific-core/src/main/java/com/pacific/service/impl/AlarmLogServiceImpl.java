package com.pacific.service.impl;

import com.pacific.common.utils.DateUtil;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.mapper.AlarmLogMapper;
import com.pacific.service.AlarmLogService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Fe on 16/6/18.
 */
public class AlarmLogServiceImpl implements AlarmLogService {

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Override
    public Long getDayAlarmLogCount() {
        AlarmLogQuery alarmLogQuery = new AlarmLogQuery();
        Date date = new Date();
        alarmLogQuery.setBeginDate(DateUtil.getBeginTimeOfDay(date).toDate());
        alarmLogQuery.setEndDate(DateUtil.getEndTimeOfDay(date).toDate());
        return alarmLogMapper.queryTotalAlarmLog(alarmLogQuery);
    }
}
