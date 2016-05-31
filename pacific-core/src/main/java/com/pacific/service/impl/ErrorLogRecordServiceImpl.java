package com.pacific.service.impl;

import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.mapper.ErrorLogRecordMapper;
import com.pacific.service.ErrorLogRecordService;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/5/31.
 */
public class ErrorLogRecordServiceImpl implements ErrorLogRecordService {

    @Resource
    private ErrorLogRecordMapper errorLogRecordMapper;

    @Override
    public ErrorLogRecord queryErrorLogRecordInDayByApplicationCode(String applicationCode) {
        return null;
    }
}
