package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.AlarmLogDto;
import com.pacific.domain.dto.AllAppErrorLogReportDto;
import com.pacific.domain.dto.AllAppErrorLogSevenDayReportDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.service.AlarmLogService;
import com.pacific.service.ApplicationService;
import com.pacific.service.ElasticSearchHelper;
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
    private ElasticSearchHelper elasticSearchHelper;

    @Resource
    private AlarmLogService alarmLogService;

    @RequestMapping("/home.htm")
    public ModelAndView home() {

        logger.info("home begin!");

        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        int appCount = 0;
        if (CollectionUtil.isNotEmpty(applicationList)) {
            appCount = applicationList.size();

        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("appCount",appCount);
        return modelAndView;
    }

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllAppErrorLogSevenDayReport.json")
    public AjaxResult queryAllAppErrorLogSevenDayReport() {
        AjaxResult ajaxResult = new AjaxResult();
        AllAppErrorLogSevenDayReportDto allAppErrorLogSevenDayReportDto = alarmLogService.queryAllAppErrorLogSevenDayReport();
        ajaxResult.setData(allAppErrorLogSevenDayReportDto);
        return ajaxResult;
    }


    @ResponseBody
    @RequestMapping(value = "/queryAllAppErrorLogDayHourReport.json")
    public AjaxResult queryAllAppErrorLogDayHourReport() {
        AjaxResult ajaxResult = new AjaxResult();
        AllAppErrorLogSevenDayReportDto allAppErrorLogSevenDayReportDto = alarmLogService.queryAllAppErrorLogSevenDayReport();
        ajaxResult.setData(allAppErrorLogSevenDayReportDto);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllAppErrorLogReport.json")
    public AjaxResult queryAllAppErrorLogReport() {
        AjaxResult ajaxResult = new AjaxResult();
        AllAppErrorLogReportDto allAppErrorLogReportDto = alarmLogService.queryAllAppErrorLogReport();
        ajaxResult.setData(allAppErrorLogReportDto);
        return ajaxResult;
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
