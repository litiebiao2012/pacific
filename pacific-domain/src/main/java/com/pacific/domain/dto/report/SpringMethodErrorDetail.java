package com.pacific.domain.dto.report;

import java.util.Date;

/**
 * Created by fe on 16/7/27.
 */
public class SpringMethodErrorDetail {
    private Date errorDateTime;
    private String errorMsg;
    private String errorStackTrace;

    public Date getErrorDateTime() {
        return errorDateTime;
    }

    public void setErrorDateTime(Date errorDateTime) {
        this.errorDateTime = errorDateTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(String errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }
}
