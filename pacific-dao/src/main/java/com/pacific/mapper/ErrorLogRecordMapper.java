package com.pacific.mapper;

import com.pacific.domain.entity.ErrorLogRecord;

import java.util.Date;
import java.util.List;

public interface ErrorLogRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErrorLogRecord record);

    int batchInsert(List<ErrorLogRecord> errorLogRecordList);

    int insertSelective(ErrorLogRecord record);

    ErrorLogRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErrorLogRecord record);

    int updateByPrimaryKeyWithBLOBs(ErrorLogRecord record);

    int updateByPrimaryKey(ErrorLogRecord record);

    ErrorLogRecord queryNewErrorLogRecordApplicationCode(String applicationCode);

    List<ErrorLogRecord> queryHasNoAlarmErrorLogRecord();
}