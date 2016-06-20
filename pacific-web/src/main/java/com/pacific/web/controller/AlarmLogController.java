package com.pacific.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fe on 16/6/20.
 */
@Controller
@RequestMapping("/alarmLog")
public class AlarmLogController {

    @RequestMapping("/toAlarmLog.htm")
    public String toAlarmLog() {
        return "alarmLog/alarmLogList";

    }

}
