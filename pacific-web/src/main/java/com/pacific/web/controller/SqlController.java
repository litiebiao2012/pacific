package com.pacific.web.controller;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.report.*;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.SqlQuery;
import com.pacific.service.SqlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Fe on 16/7/19.
 */
@Controller
@RequestMapping("/sql")
public class SqlController {

    @Resource
    private SqlService sqlService;


    @ResponseBody
    @RequestMapping("/sqlReport.json")
    public AjaxResult sqlReport(SqlQuery sqlQuery) {
        AjaxResult ajaxResult = new AjaxResult();

        Pagination<SqlReportDto> sqlReportPage = sqlService.querySqlReportPage(sqlQuery);
        ajaxResult.setData(sqlReportPage);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/sqlDetailReport.json")
    public AjaxResult sqlDetailReport(String hostName,String timeInternal,String applicationCode,String sqlHash) {
        AjaxResult ajaxResult = new AjaxResult();

        SqlDetailDto sqlDetailDto = sqlService.buildSqlDetailDto(hostName,timeInternal,applicationCode,sqlHash);
        SqlDetailReportDto sqlDetailReportDto = sqlDetailDto.getSqlDetailReportDto();
        List<SqlErrorDetail> sqlErrorDetailList =  sqlDetailDto.getSqlErrorDetailList();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("sqlDetailReport",sqlDetailReportDto);
        dataMap.put("sqlErrorDetailHtml",buildSqlErrorDetailHtml(sqlErrorDetailList));
        ajaxResult.setData(dataMap);
        return ajaxResult;
    }


    private String buildSqlErrorDetailHtml(List<SqlErrorDetail> sqlErrorDetailList) {
        StringBuffer htmlSb = new StringBuffer();
        if (CollectionUtil.isNotEmpty(sqlErrorDetailList)) {
            for (SqlErrorDetail sqlErrorDetail : sqlErrorDetailList) {
                htmlSb.append("<tr>");
                htmlSb.append("<td>");
                htmlSb.append(DateUtil.formatDateTime(sqlErrorDetail.getErrorDateTime()));
                htmlSb.append("</td>");
                htmlSb.append("<td>");
                htmlSb.append(sqlErrorDetail.getErrorMsg() == null ? "" : sqlErrorDetail.getErrorMsg());
                htmlSb.append("</td>");
                htmlSb.append("</tr>");
            }
        }
        return htmlSb.toString();
    }
}
