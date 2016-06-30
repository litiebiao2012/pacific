package com.pacific.web.controller;

import com.pacific.domain.dto.ApplicationDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.query.Pagination;
import com.pacific.mapper.ApplicationMapper;
import com.pacific.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.query.ApplicationQuery;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Resource
    private ApplicationMapper applicationMapper;

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
        Pagination<ApplicationDto> pagination = applicationService.queryAllApplicationByPage(query);
        ajaxResult.setData(pagination);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/saveApplication.json")
    public AjaxResult saveApplication(Application application) {
        AjaxResult ajaxResult = new AjaxResult();
        String applicationCode = application.getApplicationCode();
        String applicationName = application.getApplicationName();
        if (StringUtils.isEmpty(applicationCode)) {
            ajaxResult.setErrorMessage("应用编码不能为空!");
            return ajaxResult;
        }

        if (StringUtils.isEmpty(applicationName)) {
            ajaxResult.setErrorMessage("应用名称不能为空!");
            return ajaxResult;
        }
        applicationService.saveApplication(application);

        return ajaxResult;
    }

    @RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
    public ModelAndView toEdit(Long id, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            Application application = applicationMapper.selectByPrimaryKey(id);
            try {
                if (application == null) response.sendRedirect("/application/list.htm");

                mv.addObject("application",application);
                mv.setViewName("application/applicationEdit");
            } catch (IOException e) {
                //TODO ignore
            }
        } else {
            mv.setViewName("application/applicationEdit");
        }
        return mv;
    }
}

