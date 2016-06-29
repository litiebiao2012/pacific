package com.pacific.web.controller;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.AlarmLogDto;
import com.pacific.domain.query.AlarmLogQuery;
import com.pacific.domain.query.Pagination;
import com.pacific.service.AlarmLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/6/20.
 */
@Controller
@RequestMapping("/alarmLog")
public class AlarmLogController {

    @Resource
    private AlarmLogService alarmLogService;

    @RequestMapping("/list.htm")
    public String toAlarmLog() {
        return "alarmLog/alarmLogList";
    }

    @ResponseBody
    @RequestMapping("/alarmLogList.json")
    public AjaxResult list(AlarmLogQuery alarmLogQuery) {
        AjaxResult ajaxResult = new AjaxResult();
        Pagination<AlarmLogDto> alarmLogDtoPagination = alarmLogService.queryAllAlarmLogByPage(alarmLogQuery);
        ajaxResult.setData(alarmLogDtoPagination);
        return ajaxResult;
    }
}
