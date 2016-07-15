package com.pacific.domain.dto.report;

/**
 * Created by Fe on 16/7/15.
 */
public class JVMMemoryReportDto {
    private HeadMemoryDto headMemoryDto;
    private NonHeadMemoryDto nonHeadMemoryDto;


    public HeadMemoryDto getHeadMemoryDto() {
        return headMemoryDto;
    }

    public void setHeadMemoryDto(HeadMemoryDto headMemoryDto) {
        this.headMemoryDto = headMemoryDto;
    }

    public NonHeadMemoryDto getNonHeadMemoryDto() {
        return nonHeadMemoryDto;
    }

    public void setNonHeadMemoryDto(NonHeadMemoryDto nonHeadMemoryDto) {
        this.nonHeadMemoryDto = nonHeadMemoryDto;
    }
}
