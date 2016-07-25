package com.pacific.domain.dto.report;

import java.util.List;

/**
 * Created by fe on 16/7/25.
 */
public class WebUrlDetailDto {

    private WebUrlDetailReportDto webUrlDetailReportDto;
    private List<WebUrlErrorDetail> webUrlErrorDetail;

    public WebUrlDetailReportDto getWebUrlDetailReportDto() {
        return webUrlDetailReportDto;
    }

    public void setWebUrlDetailReportDto(WebUrlDetailReportDto webUrlDetailReportDto) {
        this.webUrlDetailReportDto = webUrlDetailReportDto;
    }

    public List<WebUrlErrorDetail> getWebUrlErrorDetail() {
        return webUrlErrorDetail;
    }

    public void setWebUrlErrorDetail(List<WebUrlErrorDetail> webUrlErrorDetail) {
        this.webUrlErrorDetail = webUrlErrorDetail;
    }
}
