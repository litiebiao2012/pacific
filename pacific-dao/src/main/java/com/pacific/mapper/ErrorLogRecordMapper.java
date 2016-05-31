package com.pacific.mapper;

import com.pacific.domain.entity.ErrorLogRecord;

import java.util.Date;

public interface ErrorLogRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErrorLogRecord record);

    int insertSelective(ErrorLogRecord record);

    ErrorLogRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErrorLogRecord record);

    int updateByPrimaryKeyWithBLOBs(ErrorLogRecord record);

    int updateByPrimaryKey(ErrorLogRecord record);

    ErrorLogRecord queryErrorLogRecordByParam(Date beginDate,Date endDate,String applicationId);
}