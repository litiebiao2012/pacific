package com.pacific.service;

import com.pacific.domain.dto.jvm.JVMGcDto;
import com.pacific.domain.dto.report.JVMGcReportDto;

/**
 * Created by Fe on 16/7/12.
 */
public interface JVMGcService {

    public void saveJVMGc(JVMGcDto jvmGcDto);

    public JVMGcReportDto queryJVMGcReportDto(String applicationCode, String timeInternal, String clientIp);
}
