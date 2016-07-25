package com.pacific.domain.dto.report;

import java.util.Date;

/**
 * Created by fe on 16/7/25.
 */
public class WebUrlErrorDetail {
    private Date errorDateTime;
    private String errorMsg;

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
}
