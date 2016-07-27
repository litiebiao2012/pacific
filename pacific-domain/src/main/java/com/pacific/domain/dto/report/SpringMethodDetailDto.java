package com.pacific.domain.dto.report;

import java.util.List;

/**
 * Created by fe on 16/7/27.
 */
public class SpringMethodDetailDto {
    private SpringMethodDetailReportDto springMethodDetailReportDto;
    private List<SpringMethodErrorDetail> springMethodErrorDetailList;

    public SpringMethodDetailReportDto getSpringMethodDetailReportDto() {
        return springMethodDetailReportDto;
    }

    public void setSpringMethodDetailReportDto(SpringMethodDetailReportDto springMethodDetailReportDto) {
        this.springMethodDetailReportDto = springMethodDetailReportDto;
    }

    public List<SpringMethodErrorDetail> getSpringMethodErrorDetailList() {
        return springMethodErrorDetailList;
    }

    public void setSpringMethodErrorDetailList(List<SpringMethodErrorDetail> springMethodErrorDetailList) {
        this.springMethodErrorDetailList = springMethodErrorDetailList;
    }
}
