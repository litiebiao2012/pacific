package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class WebUrl implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private String url;

    private Long count;

    private Integer concurrentMax;

    private Long nanoTotal;

    private Long nanoMax;

    private Date errorTime;

    private String isError;

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

    public Long getNanoTotal() {
        return nanoTotal;
    }

    public void setNanoTotal(Long nanoTotal) {
        this.nanoTotal = nanoTotal;
    }

    public Long getNanoMax() {
        return nanoMax;
    }

    public void setNanoMax(Long nanoMax) {
        this.nanoMax = nanoMax;
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }
}