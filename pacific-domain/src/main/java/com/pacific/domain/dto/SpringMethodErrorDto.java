package com.pacific.domain.dto;

import com.pacific.domain.entity.SpringMethod;

/**
 * Created by fe on 16/8/2.
 */
public class SpringMethodErrorDto extends SpringMethod {
    private long errorCount;

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }
}
