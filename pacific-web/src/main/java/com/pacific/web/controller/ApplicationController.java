package com.pacific.web.controller;

import com.pacific.domain.entity.Application;
import com.pacific.domain.query.Pagination;
import com.pacific.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.query.ApplicationQuery;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/6/21.
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Resource
    private ApplicationService applicationService;

    @RequestMapping("/list.htm")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("application/applicationList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/list.json")
    public AjaxResult list(ApplicationQuery query) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Application> applicationList = applicationService.queryAllApplication();
        Integer total = applicationService.getTotalApplication();
        Pagination<Application> pagination = new Pagination<Application>(query, applicationList, total);
        ajaxResult.setData(pagination);
        return ajaxResult;
    }

    @RequestMapping("/saveApplication.json")
    public AjaxResult saveApplication() {
        return null;
    }
}
