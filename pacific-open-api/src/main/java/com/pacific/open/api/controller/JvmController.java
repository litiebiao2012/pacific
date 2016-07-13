package com.pacific.open.api.controller;

import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.JVMGcDto;
import com.pacific.domain.dto.jvm.JVMInfoDto;
import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.dto.jvm.JVMThreadDto;
import com.pacific.service.JVMGcService;
import com.pacific.service.JVMInfoService;
import com.pacific.service.JVMMemoryService;
import com.pacific.service.JVMThreadService;
import org.elasticsearch.monitor.jvm.JvmInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Fe on 16/7/8.
 */
@Controller
@RequestMapping("/jvm")
public class JvmController {

    @Resource
    private JVMGcService jvmGcService;
    @Resource
    private JVMMemoryService jvmMemoryService;
    @Resource
    private JVMThreadService jvmThreadService;
    @Resource
    private JVMInfoService jvmInfoService;


    @ResponseBody
    @RequestMapping("/memory.json")
    private AjaxResult memory(JVMMemoryDto jvmMemoryDto) {
        jvmMemoryService.saveJVMMemory(jvmMemoryDto);
        return new AjaxResult();
    }

    @ResponseBody
    @RequestMapping("/gc.json")
    private AjaxResult gc(JVMGcDto jvmGcDto) {
        jvmGcService.saveJVMGc(jvmGcDto);
        return new AjaxResult();
    }

    @ResponseBody
    @RequestMapping("/thread.json")
    private AjaxResult thread(JVMThreadDto jvmThreadDto) {
        jvmThreadService.saveJVMThread(jvmThreadDto);
        return new AjaxResult();
    }


    @ResponseBody
    @RequestMapping("/info.json")
    private AjaxResult info(JVMInfoDto jvmInfoDto) {
        jvmInfoService.saveJVMInfo(jvmInfoDto);
        return new AjaxResult();
    }
}
