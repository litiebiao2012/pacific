package com.pacific.domain.dto.report;

import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/7/18.
 */
public class JVMThreadReportDto {
    private ThreadReportDto threadReportDto;
    private ThreadCpuRateReportDto threadCpuRateReportDto;

    public ThreadReportDto getThreadReportDto() {
        return threadReportDto;
    }

    public void setThreadReportDto(ThreadReportDto threadReportDto) {
        this.threadReportDto = threadReportDto;
    }

    public ThreadCpuRateReportDto getThreadCpuRateReportDto() {
        return threadCpuRateReportDto;
    }

    public void setThreadCpuRateReportDto(ThreadCpuRateReportDto threadCpuRateReportDto) {
        this.threadCpuRateReportDto = threadCpuRateReportDto;
    }
}
