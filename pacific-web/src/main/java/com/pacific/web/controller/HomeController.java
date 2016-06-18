package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.entity.Application;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.service.AlarmLogService;
import com.pacific.service.ApplicationService;
import com.pacific.service.ElasticSearchHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/5/27.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ElasticSearchHelper elasticSearchHelper;

    @Resource
    private AlarmLogService alarmLogService;

    @RequestMapping("/home.htm")
    public ModelAndView home() {
        List<Application> applicationList = applicationService.queryAllApplication();
        int appCount = 0;
        long dayAllLogTotal = 0;
        long dayErrorLogTotal = 0;
        long dayAlarmLogTotal = alarmLogService.getDayAlarmLogCount();

        if (CollectionUtil.isNotEmpty(applicationList)) {
            String[] appCodes = new String[applicationList.size()];
            int index = 0;
            for (Application app : applicationList) {
                appCodes[index] = app.getApplicationCode();
                index++;
            }
            appCount = applicationList.size();

            LoggerQuery loggerQuery = new LoggerQuery();
            Date date = new Date();
            loggerQuery.setBeginDate(DateUtil.getBeginTimeOfDay(date).toDate());
            loggerQuery.setEndDate(DateUtil.getEndTimeOfDay(date).toDate());
            dayAllLogTotal = elasticSearchHelper.queryTotalLog(appCodes,loggerQuery);
            loggerQuery.setLevel("error");
            dayErrorLogTotal = elasticSearchHelper.queryTotalLog(appCodes,loggerQuery);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("appCount",appCount);
        modelAndView.addObject("dayAllLogTotal",dayAllLogTotal);
        modelAndView.addObject("dayErrorLogTotal",dayErrorLogTotal);
        modelAndView.addObject("dayAlarmLogTotal",dayAlarmLogTotal);
        return modelAndView;
    }

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login() {
        return "login";

    }

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/check.htm",method = RequestMethod.GET)
    public void check() {
        try {
            int i = 0;
            int res = 1 / 0;
        } catch (Exception e) {
            logger.error("我的错,e : {}",e);
        }
    }
}
