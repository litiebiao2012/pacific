package com.pacific.domain.dto.jvm;

import java.util.Date;

/**
 * Created by Fe on 16/7/11.
 */
public class BaseDto {
    private String appCode;
    private String clientIp;
    private Date createTime;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
