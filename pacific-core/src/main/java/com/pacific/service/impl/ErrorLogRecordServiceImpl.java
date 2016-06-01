package com.pacific.service.impl;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.domain.enums.ErrorLogTypeEnums;
import com.pacific.domain.search.result.LoggerResult;
import com.pacific.mapper.ErrorLogRecordMapper;
import com.pacific.service.ErrorLogRecordService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/5/31.
 */
public class ErrorLogRecordServiceImpl implements ErrorLogRecordService {
    public static final Logger logger = LoggerFactory.getLogger(ErrorLogRecordServiceImpl.class);

    @Resource
    private ErrorLogRecordMapper errorLogRecordMapper;

    @Override
    public ErrorLogRecord queryErrorLogRecordInDayByApplicationCode(String applicationCode) {
        Assert.notNull(applicationCode);
        ErrorLogRecord errorLogRecord = errorLogRecordMapper.queryNewErrorLogRecordApplicationCode(applicationCode);
        return errorLogRecord;
    }

    public void batchSaveErrorLogRecord(List<LoggerResult> loggerResultList) {
        List<ErrorLogRecord> errorLogRecordList = new ArrayList<ErrorLogRecord>();
        if (CollectionUtil.isNotEmpty(loggerResultList)) {
            Date now = new Date();
            for (LoggerResult loggerResult : loggerResultList) {
                ErrorLogRecord errorLogRecord = new ErrorLogRecord();
                errorLogRecord.setCreateTime(now);
                errorLogRecord.setUpdateTime(now);
                errorLogRecord.setApplicationCode(loggerResult.getIndex());
                errorLogRecord.setElasticsearchLogId(loggerResult.getId());
                errorLogRecord.setElasticsearchLogType(loggerResult.getType());
                errorLogRecord.setElasticsearchLogScore(loggerResult.getScore());
                errorLogRecord.setElasticsearchLogCreateTime(loggerResult.getTimestamp());
                errorLogRecord.setElasticsearchLogVersion(loggerResult.getVersion());
                errorLogRecord.setLogMessage(loggerResult.getMessage());
                errorLogRecord.setLogLoggerName(loggerResult.getLoggerName());
                errorLogRecord.setLogThreadName(loggerResult.getThreadName());
                errorLogRecord.setLogLevel(loggerResult.getLevel());
                errorLogRecord.setLogValue(loggerResult.getLevelValue());
                errorLogRecord.setLogHostName(loggerResult.getHostName());
                errorLogRecord.setLogFilePath(loggerResult.getPath());
                errorLogRecord.setErrorLogType(ErrorLogTypeEnums.JAVA.getCode());
                errorLogRecord.setLogStackTrace(loggerResult.getStackTrace());

                errorLogRecordList.add(errorLogRecord);
            }
            errorLogRecordMapper.batchInsert(errorLogRecordList);
        }

    }
}
