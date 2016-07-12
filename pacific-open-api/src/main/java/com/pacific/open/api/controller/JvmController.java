package com.pacific.open.api.controller;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.JVMGcDto;
import com.pacific.domain.dto.jvm.JVMInfoDto;
import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.dto.jvm.JVMThreadDto;
import org.elasticsearch.monitor.jvm.JvmInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Fe on 16/7/8.
 */
@Controller
@RequestMapping("/jvm")
public class JvmController {

    @ResponseBody
    @RequestMapping("/memory.json")
    private AjaxResult memory(JVMMemoryDto jvmMemoryDto) {
        return null;
    }

    @ResponseBody
    @RequestMapping("/gc.json")
    private AjaxResult gc(JVMGcDto jvmGcDto) {
        return null;
    }

    @ResponseBody
    @RequestMapping("/thread.json")
    private AjaxResult thread(JVMThreadDto jvmThreadDto) {
        return null;
    }


    @ResponseBody
    @RequestMapping("/info.json")
    private AjaxResult info(JVMInfoDto jvmInfoDto) {
        return null;
    }
}
