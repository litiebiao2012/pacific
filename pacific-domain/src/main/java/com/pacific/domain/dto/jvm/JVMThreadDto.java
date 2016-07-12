package com.pacific.domain.dto.jvm;

import java.math.BigDecimal;

/**
 * Created by Fe on 16/7/11.
 */
public class JVMThreadDto extends BaseDto {

    private int daemonThreadCount;

    private int threadCount;

    private long totalStartedThreadCount;

    private int deadLockedThreadCount;

    private BigDecimal processCpuTimeRate;

    public int getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public void setDaemonThreadCount(int daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public long getTotalStartedThreadCount() {
        return totalStartedThreadCount;
    }

    public void setTotalStartedThreadCount(long totalStartedThreadCount) {
        this.totalStartedThreadCount = totalStartedThreadCount;
    }

    public int getDeadLockedThreadCount() {
        return deadLockedThreadCount;
    }

    public void setDeadLockedThreadCount(int deadLockedThreadCount) {
        this.deadLockedThreadCount = deadLockedThreadCount;
    }

    public BigDecimal getProcessCpuTimeRate() {
        return processCpuTimeRate;
    }

    public void setProcessCpuTimeRate(BigDecimal processCpuTimeRate) {
        this.processCpuTimeRate = processCpuTimeRate;
    }
}
