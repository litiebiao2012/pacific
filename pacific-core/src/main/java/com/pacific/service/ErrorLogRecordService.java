package com.pacific.service;

import com.pacific.domain.entity.ErrorLogRecord;

import java.util.List;

/**
 * Created by Fe on 16/5/31.
 */
public interface ErrorLogRecordService {

    /**
     * 查询当天最近一次load到的errorLog
     * @param applicationCode
     * @return
     */
    public ErrorLogRecord queryErrorLogRecordInDayByApplicationCode(String applicationCode);
}
