package com.pacific.domain.dto.report;

/**
 * Created by fe on 16/7/26.
 */
public class SpringMethodReportDto {
    private String className;
    private String method;
    private long count;
    private long concurrentMax;
    private long avgNano;
    private long nanoMax;
    private long errorCount;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getConcurrentMax() {
        return concurrentMax;
    }

    public void setConcurrentMax(long concurrentMax) {
        this.concurrentMax = concurrentMax;
    }

    public long getAvgNano() {
        return avgNano;
    }

    public void setAvgNano(long avgNano) {
        this.avgNano = avgNano;
    }

    public long getNanoMax() {
        return nanoMax;
    }

    public void setNanoMax(long nanoMax) {
        this.nanoMax = nanoMax;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }
}
