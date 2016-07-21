package com.pacific.service;

import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.dto.report.HeadMemoryDto;
import com.pacific.domain.dto.report.JVMMemoryReportDto;

/**
 * Created by Fe on 16/7/12.
 */
public interface JVMMemoryService {

    public void saveJVMMemory(JVMMemoryDto jvmMemoryDto);

    public JVMMemoryReportDto queryJVMMemoryDto(String applicationCode, String timeInternal, String clientIp);

}
