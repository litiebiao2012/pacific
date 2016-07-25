package com.pacific.web.controller;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.report.WebUrlReportDto;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;
import com.pacific.service.WebUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/7/19.
 */
@Controller
@RequestMapping("/webUrl")
public class WebUrlController {

    @Resource
    private WebUrlService webUrlService;

    @ResponseBody
    @RequestMapping("/webUrlReport.json")
    public AjaxResult webUrlReport(WebUrlQuery webUrlQuery) {
        AjaxResult ajaxResult = new AjaxResult();
        Pagination<WebUrlReportDto> webUrlPagination = webUrlService.queryWebUrlPage(webUrlQuery);
        ajaxResult.setData(webUrlPagination);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/webUrlDetailReport.json")
    public AjaxResult webUrlDetailReport(WebUrlQuery webUrlQuery) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }
}
