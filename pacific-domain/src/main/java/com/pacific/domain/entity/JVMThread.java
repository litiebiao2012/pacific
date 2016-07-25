package com.pacific.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JVMThread implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private Integer daemonThreadCount;

    private Integer threadCount;

    private Long totalStartedThreadCount;

    private Integer deadLockedThreadCount;

    private BigDecimal processCpuTimeRate;

    private String hostName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode == null ? null : applicationCode.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
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

    public Integer getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public void setDaemonThreadCount(Integer daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Long getTotalStartedThreadCount() {
        return totalStartedThreadCount;
    }

    public void setTotalStartedThreadCount(Long totalStartedThreadCount) {
        this.totalStartedThreadCount = totalStartedThreadCount;
    }

    public Integer getDeadLockedThreadCount() {
        return deadLockedThreadCount;
    }

    public void setDeadLockedThreadCount(Integer deadLockedThreadCount) {
        this.deadLockedThreadCount = deadLockedThreadCount;
    }

    public BigDecimal getProcessCpuTimeRate() {
        return processCpuTimeRate;
    }

    public void setProcessCpuTimeRate(BigDecimal processCpuTimeRate) {
        this.processCpuTimeRate = processCpuTimeRate;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}