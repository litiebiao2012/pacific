package com.pacific.web.controller;

import com.pacific.common.utils.VelocityTemplateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.report.JVMMemoryReportDto;
import com.pacific.domain.entity.Machine;
import com.pacific.mapper.MachineMapper;
import com.pacific.service.JVMMemoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/7/13.
 */
@Controller
@RequestMapping("/jvm")
public class JVMController {

    @Resource
    private MachineMapper machineMapper;

    @Resource
    private JVMMemoryService jvmMemoryService;


    @RequestMapping("/jvmDetail.htm")
    public ModelAndView jvmDetail(String applicationCode) {
        ModelAndView modelAndView = new ModelAndView();

        List<Machine> machineList = machineMapper.selectAllMachineByApplicationCode(applicationCode);
        modelAndView.addObject("machineList",machineList);
        modelAndView.addObject("applicationCode",applicationCode);
        modelAndView.setViewName("jvm/jvmDetail");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/loadHtmlView.json")
    public AjaxResult loadHtmlView(String clientIp,String timeInternal,String type,
                                   String applicationCode) {
        String content = VelocityTemplateUtil.getContent("vm/" + type + ".vm",null);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(content);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/report.json")
    public AjaxResult report(String clientIp,String timeInternal,String applicationCode) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String,Object> reportMap = new HashMap<String,Object>();
        JVMMemoryReportDto jvmMemoryReportDto = jvmMemoryService.queryHeadMemoryDto(applicationCode,timeInternal,clientIp);
        reportMap.put("headReport",jvmMemoryReportDto.getHeadMemoryDto());
        reportMap.put("nonHeadReport",jvmMemoryReportDto.getNonHeadMemoryDto());
        ajaxResult.setData(reportMap);
        return ajaxResult;
    }
}
