package com.pacific.domain.dto.report;

/**
 * Created by Fe on 16/7/20.
 */
public class JVMGcReportDto extends BaseReportDto {
    private GcCountDto gcCountDto;
    private GcTimeDto gcTimeDto;

    public GcCountDto getGcCountDto() {
        return gcCountDto;
    }

    public void setGcCountDto(GcCountDto gcCountDto) {
        this.gcCountDto = gcCountDto;
    }

    public GcTimeDto getGcTimeDto() {
        return gcTimeDto;
    }

    public void setGcTimeDto(GcTimeDto gcTimeDto) {
        this.gcTimeDto = gcTimeDto;
    }
}
