package com.pacific.domain.search.query;

import java.util.Date;

/**
 * Created by Fe on 16/5/30.
 */
public class LoggerQuery extends ElasticSearchBaseQuery{
    private String level;
    private Date beginDate;
    private Date endDate;
    private String message;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
