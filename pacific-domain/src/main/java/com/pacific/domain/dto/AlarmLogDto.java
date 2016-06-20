package com.pacific.domain.dto;

import com.pacific.domain.entity.AlarmLog;

/**
 * Created by Fe on 16/6/18.
 */
public class AlarmLogDto extends AlarmLog {
    private String userName;
    private String applicationName;

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
}
