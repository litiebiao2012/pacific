package com.pacific.domain.search.query;

import com.pacific.domain.query.BaseQuery;

import java.util.Date;

/**
 * Created by fe on 16/7/25.
 */
public class WebUrlQuery extends BaseQuery {
    private String applicationCode;
    private String clientIp;
    private String timeInternal;
    private Date beginDate;
    private Date endDate;

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTimeInternal() {
        return timeInternal;
    }

    public void setTimeInternal(String timeInternal) {
        this.timeInternal = timeInternal;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
