package com.pacific.service;

import com.pacific.domain.dto.jvm.WebUrlInfo;
import com.pacific.domain.dto.report.WebUrlReportDto;
import com.pacific.domain.entity.WebUrl;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;

import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public interface WebUrlService {

    public void saveWebUrlInfo(String appCode,String clientIp,List<WebUrlInfo> webUrlInfoList);

    public Pagination<WebUrlReportDto> queryWebUrlPage(WebUrlQuery webUrlQuery);
}
