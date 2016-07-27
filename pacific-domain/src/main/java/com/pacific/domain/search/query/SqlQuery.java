package com.pacific.domain.search.query;

import com.pacific.domain.query.BaseQuery;

import java.util.Date;

/**
 * Created by fe on 16/7/27.
 */
public class SqlQuery extends BaseQuery {

    private String applicationCode;
    private String clientIp;
    private String hostName;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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
