package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.AlarmLogDto;
import com.pacific.domain.dto.AllAppErrorLogReportDto;
import com.pacific.domain.dto.AllAppErrorLogSevenDayReportDto;
import com.pacific.domain.dto.report.SevenDayWebUrlReport;
import com.pacific.domain.dto.report.SqlAvgTimeReport;
import com.pacific.domain.entity.Application;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.mapper.AlarmLogMapper;
import com.pacific.mapper.SpringMethodMapper;
import com.pacific.mapper.WebUrlMapper;
import com.pacific.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private AlarmLogService alarmLogService;

    @Resource
    private WebUrlMapper webUrlMapper;

    @Resource
    private SpringMethodMapper springMethodMapper;

    @Resource
    private WebUrlService webUrlService;

    @Resource
    private SqlService sqlService;

    @Resource
    private AlarmLogMapper alarmLogMapper;


    @RequestMapping("/home.htm")
    public ModelAndView home() {
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        Date date = new Date();
        int nowDayTotal = webUrlMapper.queryTotalByParam("",DateUtil.getBeginTimeOfDay(date).toDate(),DateUtil.getEndTimeOfDay(date).toDate(),"");
        int appCount = 0;
        if (CollectionUtil.isNotEmpty(applicationList)) {
            appCount = applicationList.size();
        }
        int nowDayTotalErrorSm = springMethodMapper.queryErrorTotalSm(DateUtil.getBeginTimeOfDay(date).toDate(),DateUtil.getEndTimeOfDay(date).toDate());
        AlarmLogQuery alarmLogQuery = new AlarmLogQuery();
        alarmLogQuery.setPageSize(20);
        List<AlarmLogDto> alarmLogDtoList = alarmLogMapper.queryAlarmLog(alarmLogQuery);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("appCount",appCount);
        modelAndView.addObject("nowDayTotal",nowDayTotal);
        modelAndView.addObject("nowDayTotalErrorSm",nowDayTotalErrorSm);
        modelAndView.addObject("alarmLogDtoList",alarmLogDtoList);
        return modelAndView;
    }

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllAppWebUrLSevenDayReport.json")
    public AjaxResult queryAllAppWebUrLSevenDayReport() {
        AjaxResult ajaxResult = new AjaxResult();
        SevenDayWebUrlReport sevenDayWebUrlReport = webUrlService.querySevenDayWebUrlReport("n");
        ajaxResult.setData(sevenDayWebUrlReport);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllAppErrorLogSevenDayReport.json")
    public AjaxResult queryAllAppErrorLogSevenDayReport() {
        AjaxResult ajaxResult = new AjaxResult();
        SevenDayWebUrlReport sevenDayWebUrlReport = webUrlService.querySevenDayWebUrlReport("y");
        ajaxResult.setData(sevenDayWebUrlReport);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllAppSqlAvgTime.json")
    public AjaxResult queryAllAppSqlAvgTime() {
        AjaxResult ajaxResult = new AjaxResult();
        SqlAvgTimeReport sqlAvgTimeReport = sqlService.queryDaySqlAvgTimeReport();
        ajaxResult.setData(sqlAvgTimeReport);
        return ajaxResult;
    }


}
