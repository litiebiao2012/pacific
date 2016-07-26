package com.pacific.web.controller;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.report.WebUrlDetailDto;
import com.pacific.domain.dto.report.WebUrlDetailReportDto;
import com.pacific.domain.dto.report.WebUrlErrorDetail;
import com.pacific.domain.dto.report.WebUrlReportDto;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;
import com.pacific.service.WebUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        webUrlQuery.setPageSize(50);
        Pagination<WebUrlReportDto> webUrlPagination = webUrlService.queryWebUrlPage(webUrlQuery);
        ajaxResult.setData(webUrlPagination);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/webUrlDetailReport.json")
    public AjaxResult webUrlDetailReport(String clientIp,String timeInternal,String applicationCode,String url) {
        AjaxResult ajaxResult = new AjaxResult();

        WebUrlDetailDto webUrlDetailDto = webUrlService.buildWebUrlDetailDto(clientIp,timeInternal,applicationCode,url);
        WebUrlDetailReportDto webUrlDetailReportDto = webUrlDetailDto.getWebUrlDetailReportDto();
        List<WebUrlErrorDetail> webUrlErrorDetailList =  webUrlDetailDto.getWebUrlErrorDetail();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("webUrlDetailReport",webUrlDetailReportDto);
        dataMap.put("webUrlErrorDetailHtml",buildWebUrlErrorDetailHtml(webUrlErrorDetailList));
        ajaxResult.setData(dataMap);
        return ajaxResult;
    }

    /**
     * <tr>
     <td>
     2016-05-14 23:22:22
     </td>
     <td>
     sfowfewfefe
     </td>
     </tr>
     <tr>
     <td>
     2016-05-14 23:22:22
     </td>
     <td>
     sfowfewfefe
     </td>
     </tr>
     * @param webUrlErrorDetailList
     * @return
     */
    private String buildWebUrlErrorDetailHtml(List<WebUrlErrorDetail> webUrlErrorDetailList) {
        StringBuffer htmlSb = new StringBuffer();
        if (CollectionUtil.isNotEmpty(webUrlErrorDetailList)) {
            for (WebUrlErrorDetail webUrlErrorDetail : webUrlErrorDetailList) {
                htmlSb.append("<tr>");
                htmlSb.append("<td>");
                htmlSb.append(DateUtil.formatDateTime(webUrlErrorDetail.getErrorDateTime()));
                htmlSb.append("</td>");
                htmlSb.append("<td>");
                htmlSb.append(webUrlErrorDetail.getErrorMsg() == null ? "" : webUrlErrorDetail.getErrorMsg());
                htmlSb.append("</td>");
                htmlSb.append("</tr>");
            }
        }
        return htmlSb.toString();

    }
}
