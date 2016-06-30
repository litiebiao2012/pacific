package com.pacific.web.controller;

import com.pacific.common.Constants;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.entity.Application;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.ApplicationQuery;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.domain.search.result.LoggerResult;
import com.pacific.service.ApplicationService;
import com.pacific.service.ElasticSearchHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fe on 16/6/27.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Resource
    private ElasticSearchHelper elasticSearchHelper;

    @Resource
    private ApplicationService applicationService;

    @ResponseBody
    @RequestMapping("/list.json")
    public AjaxResult list(LoggerQuery loggerQuery) {
        AjaxResult ajaxResult = new AjaxResult();

        if (StringUtils.isNotEmpty(loggerQuery.getLevel())) {
            if (loggerQuery.getLevel().equals("all")) {
                loggerQuery.setLevel(null);
            }
        }
        loggerQuery.setType(Constants.DEFAULT_ELASTIC_SEARCH_LOG_TYPE);


        List<LoggerResult> list = elasticSearchHelper.searchNewErrorLog(loggerQuery);
        Long total = elasticSearchHelper.queryTotalLog(loggerQuery.getIndex(),loggerQuery);
        Pagination<LoggerResult> loggerResultPagination = new Pagination<LoggerResult>(loggerQuery,list,total.intValue());
        ajaxResult.setData(loggerResultPagination);
        return ajaxResult;
    }

    @RequestMapping("/list.htm")
    public ModelAndView toLogList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        modelAndView.addObject("applicationList",applicationList);
        modelAndView.setViewName("log/logList");
        return modelAndView;
    }
}

