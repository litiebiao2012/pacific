package com.pacific.mapper;

import com.pacific.domain.dto.AlarmLogDto;
import com.pacific.domain.entity.AlarmLog;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.domain.search.query.LoggerQuery;

import java.util.List;

public interface AlarmLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlarmLog record);

    int insertSelective(AlarmLog record);

    AlarmLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmLog record);

    int updateByPrimaryKey(AlarmLog record);

    long queryTotalAlarmLog(AlarmLogQuery alarmLogQuery);

    List<AlarmLogDto> queryAlarmLog(AlarmLogQuery alarmLogQuery);

}