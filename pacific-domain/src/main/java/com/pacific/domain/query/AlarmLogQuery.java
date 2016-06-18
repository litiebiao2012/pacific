package com.pacific.domain.query;

import java.util.Date;

/**
 * Created by Fe on 16/6/18.
 */
public class AlarmLogQuery extends BaseQuery {
    private Date beginDate;
    private Date endDate;

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
