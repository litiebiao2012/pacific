package com.pacific.mapper;

import com.pacific.domain.entity.ErrorLogRecord;

public interface ErrorLogRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErrorLogRecord record);

    int insertSelective(ErrorLogRecord record);

    ErrorLogRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErrorLogRecord record);

    int updateByPrimaryKeyWithBLOBs(ErrorLogRecord record);

    int updateByPrimaryKey(ErrorLogRecord record);
}