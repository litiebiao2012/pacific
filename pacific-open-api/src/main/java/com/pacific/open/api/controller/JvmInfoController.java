package com.pacific.open.api.controller;

import com.pacific.common.web.result.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Fe on 16/7/8.
 */
@Controller
@RequestMapping("/jvm")
public class JvmInfoController {

    @ResponseBody
    @RequestMapping("/memory.json")
    private AjaxResult memory() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/gc.json")
    private AjaxResult gc() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/thread.json")
    private AjaxResult thread() {
        return null;
    }


    @ResponseBody
    @RequestMapping("/info.json")
    private AjaxResult info() {
        return null;
    }
}
