package com.pacific.domain.dto.jvm;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Fe on 16/7/20.
 */
public class JdbcInfoDetail {

    @JSONField(name = "URL")
    private String url;
    @JSONField(name = "Username")
    private String userName;
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "DbType")
    private String dbType;
    @JSONField(name = "MaxActive")
    private int    maxActive;
    @JSONField(name = "MinIdle")
    private int    minIdle;
    @JSONField(name = "PoolingCount")
    private int    poolingCount;
    @JSONField(name = "ErrorCount")
    private long   errorCount;
    @JSONField(name = "DriverClassName")
    private String driverClassName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getPoolingCount() {
        return poolingCount;
    }

    public void setPoolingCount(int poolingCount) {
        this.poolingCount = poolingCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
