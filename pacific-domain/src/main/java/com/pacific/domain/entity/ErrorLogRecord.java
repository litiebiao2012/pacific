package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class ErrorLogRecord implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String applicationCode;

    private String elasticsearchLogId;

    private String elasticsearchLogType;

    private Integer elasticsearchLogScore;

    private Date elasticsearchLogCreateTime;

    private Integer elasticsearchLogVersion;

    private String logMessage;

    private String logLoggerName;

    private String logThreadName;

    private String logLevel;

    private Integer logValue;

    private String logHostName;

    private String logFilePath;

    private String errorLogType;

    private String logStackTrace;

    private String isNotify;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode == null ? null : applicationCode.trim();
    }

    public String getElasticsearchLogId() {
        return elasticsearchLogId;
    }

    public void setElasticsearchLogId(String elasticsearchLogId) {
        this.elasticsearchLogId = elasticsearchLogId == null ? null : elasticsearchLogId.trim();
    }

    public String getElasticsearchLogType() {
        return elasticsearchLogType;
    }

    public void setElasticsearchLogType(String elasticsearchLogType) {
        this.elasticsearchLogType = elasticsearchLogType == null ? null : elasticsearchLogType.trim();
    }

    public Integer getElasticsearchLogScore() {
        return elasticsearchLogScore;
    }

    public void setElasticsearchLogScore(Integer elasticsearchLogScore) {
        this.elasticsearchLogScore = elasticsearchLogScore;
    }

    public Date getElasticsearchLogCreateTime() {
        return elasticsearchLogCreateTime;
    }

    public void setElasticsearchLogCreateTime(Date elasticsearchLogCreateTime) {
        this.elasticsearchLogCreateTime = elasticsearchLogCreateTime;
    }

    public Integer getElasticsearchLogVersion() {
        return elasticsearchLogVersion;
    }

    public void setElasticsearchLogVersion(Integer elasticsearchLogVersion) {
        this.elasticsearchLogVersion = elasticsearchLogVersion;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage == null ? null : logMessage.trim();
    }

    public String getLogLoggerName() {
        return logLoggerName;
    }

    public void setLogLoggerName(String logLoggerName) {
        this.logLoggerName = logLoggerName == null ? null : logLoggerName.trim();
    }

    public String getLogThreadName() {
        return logThreadName;
    }

    public void setLogThreadName(String logThreadName) {
        this.logThreadName = logThreadName == null ? null : logThreadName.trim();
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel == null ? null : logLevel.trim();
    }

    public Integer getLogValue() {
        return logValue;
    }

    public void setLogValue(Integer logValue) {
        this.logValue = logValue;
    }

    public String getLogHostName() {
        return logHostName;
    }

    public void setLogHostName(String logHostName) {
        this.logHostName = logHostName == null ? null : logHostName.trim();
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath == null ? null : logFilePath.trim();
    }

    public String getErrorLogType() {
        return errorLogType;
    }

    public void setErrorLogType(String errorLogType) {
        this.errorLogType = errorLogType == null ? null : errorLogType.trim();
    }

    public String getLogStackTrace() {
        return logStackTrace;
    }

    public void setLogStackTrace(String logStackTrace) {
        this.logStackTrace = logStackTrace == null ? null : logStackTrace.trim();
    }

    public String getIsNotify() {
        return isNotify;
    }

    public void setIsNotify(String isNotify) {
        this.isNotify = isNotify;
    }
}