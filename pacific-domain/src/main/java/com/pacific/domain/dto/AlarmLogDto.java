package com.pacific.domain.dto;

import com.pacific.domain.entity.AlarmLog;

/**
 * Created by Fe on 16/6/18.
 */
public class AlarmLogDto extends AlarmLog {
    private String userName;
    private String applicationName;
    private String logMessage;
    private String logHostName;
    private String logFilePath;

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogHostName() {
        return logHostName;
    }

    public void setLogHostName(String logHostName) {
        this.logHostName = logHostName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
}
