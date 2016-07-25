package com.pacific.domain.dto.report;

/**
 * Created by fe on 16/7/25.
 */
public class WebUrlReportDto {
    private String url;
    private long total;
    private long concurrentMax;
    private long avgNano;
    private long nanoMax;
    private int errorCount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }
}
