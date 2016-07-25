package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class JVMGc implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private Long youngGcCollectionCount;

    private Long youngGcCollectionTime;

    private Long fullGcCollectionCount;

    private Long fullGcCollectionTime;

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

    public Long getYoungGcCollectionCount() {
        return youngGcCollectionCount;
    }

    public void setYoungGcCollectionCount(Long youngGcCollectionCount) {
        this.youngGcCollectionCount = youngGcCollectionCount;
    }

    public Long getYoungGcCollectionTime() {
        return youngGcCollectionTime;
    }

    public void setYoungGcCollectionTime(Long youngGcCollectionTime) {
        this.youngGcCollectionTime = youngGcCollectionTime;
    }

    public Long getFullGcCollectionCount() {
        return fullGcCollectionCount;
    }

    public void setFullGcCollectionCount(Long fullGcCollectionCount) {
        this.fullGcCollectionCount = fullGcCollectionCount;
    }

    public Long getFullGcCollectionTime() {
        return fullGcCollectionTime;
    }

    public void setFullGcCollectionTime(Long fullGcCollectionTime) {
        this.fullGcCollectionTime = fullGcCollectionTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}