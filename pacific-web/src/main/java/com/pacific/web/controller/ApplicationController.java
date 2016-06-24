package com.pacific.web.controller;

import com.pacific.domain.entity.Application;
import com.pacific.domain.query.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.query.ApplicationQuery;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Fe on 16/6/21.
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

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

        Application application1 = new Application();
        application1.setId(1l);
        application1.setApplicationCode("app");
        application1.setApplicationName("应用");
        application1.setState("normal");
        application1.setCreateTime(new Date());

        Application application = new Application();
        application.setId(2l);
        application.setApplicationCode("app2");
        application.setApplicationName("应用2");
        application.setState("normal2");
        application.setCreateTime(new Date());



        Pagination<Application> pagination = new Pagination<Application>(query, Arrays.asList(application1, application), 2);

        ajaxResult.setData(pagination);

//        ajaxResult.setStatus(AjaxResult.STATUS_PARAM_ERROR);

        return ajaxResult;
    }

    @RequestMapping("/saveApplication.json")
    public AjaxResult saveApplication() {
        return null;
    }
}
