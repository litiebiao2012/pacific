package com.pacific.web.controller;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.report.SpringMethodDetailDto;
import com.pacific.domain.dto.report.SpringMethodDetailReportDto;
import com.pacific.domain.dto.report.SpringMethodErrorDetail;
import com.pacific.domain.dto.report.SpringMethodReportDto;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SpringMethodQuery;
import com.pacific.service.SpringMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fe on 16/7/26.
 */
@Controller
@RequestMapping("/sm")
public class SpringMethodController {

    @Resource
    private SpringMethodService springMethodService;

    @ResponseBody
    @RequestMapping("/springMethodReport.json")
    public AjaxResult springMethodReport(SpringMethodQuery springMethodQuery) {
        AjaxResult ajaxResult = new AjaxResult();
        springMethodQuery.setPageSize(50);
        Pagination<SpringMethodReportDto> springMethodReportDtoPagination = springMethodService.querySpringMethodReport(springMethodQuery);
        ajaxResult.setData(springMethodReportDtoPagination);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/springMethodDetailReport.json")
    public AjaxResult springMethodDetailReport(String hostName,String timeInternal,String applicationCode,String method) {
        AjaxResult ajaxResult = new AjaxResult();
        SpringMethodDetailDto springMethodDetailDto = springMethodService.buildSpringMethodDetailDto(hostName,timeInternal,applicationCode, method);
        SpringMethodDetailReportDto springMethodDetailReportDto = springMethodDetailDto.getSpringMethodDetailReportDto();
        List<SpringMethodErrorDetail> springMethodErrorDetailList =  springMethodDetailDto.getSpringMethodErrorDetailList();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("springMethodDetailReport",springMethodDetailReportDto);
        dataMap.put("springMethodErrorDetailHtml",buildSpringMethodErrorHtml(springMethodErrorDetailList));
        ajaxResult.setData(dataMap);
        return ajaxResult;
    }

    private String buildSpringMethodErrorHtml(List<SpringMethodErrorDetail> webUrlErrorDetailList) {
        StringBuffer htmlSb = new StringBuffer();
        if (CollectionUtil.isNotEmpty(webUrlErrorDetailList)) {
            for (SpringMethodErrorDetail springMethodErrorDetail : webUrlErrorDetailList) {
                htmlSb.append("<tr>");
                htmlSb.append("<td>");
                htmlSb.append(DateUtil.formatDateTime(springMethodErrorDetail.getErrorDateTime()));
                htmlSb.append("</td>");
                htmlSb.append("<td>");
                htmlSb.append(springMethodErrorDetail.getErrorMsg() == null ? "" : springMethodErrorDetail.getErrorMsg());
                htmlSb.append("</td>");
                htmlSb.append("</tr>");
            }
        }
        return htmlSb.toString();

    }
}
