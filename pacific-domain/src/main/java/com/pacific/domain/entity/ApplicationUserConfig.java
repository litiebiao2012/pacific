package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class ApplicationUserConfig implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String channelConfig;

    private String isMonitorAllErrorLog;

    private String monitorErrorLogKeywords;

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

    public String getChannelConfig() {
        return channelConfig;
    }

    public void setChannelConfig(String channelConfig) {
        this.channelConfig = channelConfig == null ? null : channelConfig.trim();
    }

    public String getIsMonitorAllErrorLog() {
        return isMonitorAllErrorLog;
    }

    public void setIsMonitorAllErrorLog(String isMonitorAllErrorLog) {
        this.isMonitorAllErrorLog = isMonitorAllErrorLog == null ? null : isMonitorAllErrorLog.trim();
    }

    public String getMonitorErrorLogKeywords() {
        return monitorErrorLogKeywords;
    }

    public void setMonitorErrorLogKeywords(String monitorErrorLogKeywords) {
        this.monitorErrorLogKeywords = monitorErrorLogKeywords == null ? null : monitorErrorLogKeywords.trim();
    }
}