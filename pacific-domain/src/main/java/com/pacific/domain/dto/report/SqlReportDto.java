package com.pacific.domain.dto.report;

/**
 * Created by fe on 16/7/27.
 */
public class SqlReportDto {
    private String sql;
    private String sqlHash;
    private long count;
    private long concurrentMax;
    private long avgTime;
    private long maxTime;
    private long errorCount;
    private long avgFetchRowCount;
    private long maxFetchRowCount;
    private long avgEffectedRowCount;
    private long maxEffectedRowCount;


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(long avgTime) {
        this.avgTime = avgTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getAvgFetchRowCount() {
        return avgFetchRowCount;
    }

    public void setAvgFetchRowCount(long avgFetchRowCount) {
        this.avgFetchRowCount = avgFetchRowCount;
    }

    public long getMaxFetchRowCount() {
        return maxFetchRowCount;
    }

    public void setMaxFetchRowCount(long maxFetchRowCount) {
        this.maxFetchRowCount = maxFetchRowCount;
    }

    public long getAvgEffectedRowCount() {
        return avgEffectedRowCount;
    }

    public void setAvgEffectedRowCount(long avgEffectedRowCount) {
        this.avgEffectedRowCount = avgEffectedRowCount;
    }

    public long getMaxEffectedRowCount() {
        return maxEffectedRowCount;
    }

    public void setMaxEffectedRowCount(long maxEffectedRowCount) {
        this.maxEffectedRowCount = maxEffectedRowCount;
    }

    public String getSqlHash() {
        return sqlHash;
    }

    public void setSqlHash(String sqlHash) {
        this.sqlHash = sqlHash;
    }

    public long getConcurrentMax() {
        return concurrentMax;
    }

    public void setConcurrentMax(long concurrentMax) {
        this.concurrentMax = concurrentMax;
    }
}
