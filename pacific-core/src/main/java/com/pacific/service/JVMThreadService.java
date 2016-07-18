package com.pacific.service;

import com.pacific.domain.dto.jvm.JVMThreadDto;
import com.pacific.domain.dto.report.JVMThreadReportDto;

/**
 * Created by Fe on 16/7/12.
 */
public interface JVMThreadService {

    public void saveJVMThread(JVMThreadDto jvmThreadDto);

    public JVMThreadReportDto queryHeadMemoryDto(String applicationCode, String timeInternal, String clientIp);
}
