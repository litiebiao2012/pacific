package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Sql implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private String url;

    private Long sqlHash;

    private Long count;

    private Integer concurrentMax;

    private Long totalTime;

    private Long maxTime;

    private Long fetchRowCount;

    private Long fetchRowCountMax;

    private Long effectedRowCount;

    private Long effectedRowCountMax;

    private Date errorTime;

    private String isError;

    private String sqlText;

    private String errorMsg;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getSqlHash() {
        return sqlHash;
    }

    public void setSqlHash(Long sqlHash) {
        this.sqlHash = sqlHash;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getConcurrentMax() {
        return concurrentMax;
    }

    public void setConcurrentMax(Integer concurrentMax) {
        this.concurrentMax = concurrentMax;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public Long getFetchRowCount() {
        return fetchRowCount;
    }

    public void setFetchRowCount(Long fetchRowCount) {
        this.fetchRowCount = fetchRowCount;
    }

    public Long getFetchRowCountMax() {
        return fetchRowCountMax;
    }

    public void setFetchRowCountMax(Long fetchRowCountMax) {
        this.fetchRowCountMax = fetchRowCountMax;
    }

    public Long getEffectedRowCount() {
        return effectedRowCount;
    }

    public void setEffectedRowCount(Long effectedRowCount) {
        this.effectedRowCount = effectedRowCount;
    }

    public Long getEffectedRowCountMax() {
        return effectedRowCountMax;
    }

    public void setEffectedRowCountMax(Long effectedRowCountMax) {
        this.effectedRowCountMax = effectedRowCountMax;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError == null ? null : isError.trim();
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}