package com.pacific.web.controller;

import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.utils.VelocityTemplateUtil;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.JVMInfoDetailDto;
import com.pacific.domain.dto.report.JVMGcReportDto;
import com.pacific.domain.dto.report.JVMMemoryReportDto;
import com.pacific.domain.dto.report.JVMThreadReportDto;
import com.pacific.domain.dto.report.WebUrlReportDto;
import com.pacific.domain.entity.JVMInfo;
import com.pacific.domain.entity.Machine;
import com.pacific.domain.entity.WebUrl;
import com.pacific.domain.enums.MonitorTypeEnums;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.search.query.WebUrlQuery;
import com.pacific.mapper.JVMInfoMapper;
import com.pacific.mapper.MachineMapper;
import com.pacific.mapper.WebUrlMapper;
import com.pacific.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolContext;
import org.elasticsearch.monitor.jvm.JvmInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private JVMThreadService jvmThreadService;

    @Resource
    private JVMGcService jvmGcService;

    @Resource
    private JVMInfoMapper jvmInfoMapper;

    @Resource
    private WebUrlService webUrlService;


    @RequestMapping("/jvmDetail.htm")
    public ModelAndView jvmDetail(String applicationCode,@RequestParam(defaultValue = "jvmReport") String type,String clientIp) {
        ModelAndView modelAndView = new ModelAndView();
        String viewName = "jvm/jvmReport";
        if (type.equals("jvmReport")) {
            viewName = "jvm/jvmReport";
        }

        if (clientIp != null && clientIp.equals("all")) clientIp = null;
        if (type.equals("jvmInfo")) {
            List<JVMInfo> jvmInfoList = jvmInfoMapper.selectByParam(applicationCode,clientIp);
            if (CollectionUtil.isNotEmpty(jvmInfoList)) {
                Map<String,List<JVMInfoDetailDto>> allJvmInfoMap = new LinkedHashMap<String,List<JVMInfoDetailDto>>();
                for (JVMInfo jvmInfo : jvmInfoList) {
                    List<JVMInfoDetailDto> jvmInfoDetailDtoList = new LinkedList<JVMInfoDetailDto>();
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("主机名",jvmInfo.getHostName()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("IP",jvmInfo.getClientIp()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("进程ID",jvmInfo.getPid()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("启动时间", DateUtil.formatDate(jvmInfo.getJvmStartTime())));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("启动参数",jvmInfo.getInputArguments()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("硬件平台",jvmInfo.getArch()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("可用CPU个数",jvmInfo.getAvailableProcessors() + ""));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("操作系统",jvmInfo.getOsName()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("文件编码",jvmInfo.getFileEncode()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("JVM名称",jvmInfo.getJvm()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("JavaVersion",jvmInfo.getJavaVersion()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("JavaSpecVersion",jvmInfo.getJavaSpecificationVersion()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("JavaHome",jvmInfo.getJavaHome()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("JavaLibraryPath",jvmInfo.getJavaLibraryPath()));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("当前装载的类总数",jvmInfo.getLoadedClassCount() + ""));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("总共装载过的类总数",jvmInfo.getTotalLoadedClassCount() + ""));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("卸载的类总数",jvmInfo.getUnloadedClassCount() + ""));
                    jvmInfoDetailDtoList.add(JVMInfoDetailDto.buildJVMInfoDetail("总共编译时间",jvmInfo.getTotalCompilationTime() + ""));

                    allJvmInfoMap.put(jvmInfo.getHostName(),jvmInfoDetailDtoList);
                }
                modelAndView.addObject("allJvmInfoMap",allJvmInfoMap);
            }
            viewName = "jvm/jvmInfoReport";
        }

        if (type.equals("webUrl")) {
            viewName = "jvm/webUrl";
        }

        if (type.equals("springMethod")) {
            viewName = "jvm/springMethod";
        }

        if (type.equals("druidDatasource")) {
            viewName = "jvm/druidDatasource";
        }

        if (type.equals("sql")) {
            viewName = "jvm/sql";
        }
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping("/jvmInfoReport.htm")
    public ModelAndView jvmInfoReport(String applicationCode) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jvm/jvmInfoReport");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/loadHtmlView.json")
    public AjaxResult loadHtmlView(String clientIp,String timeInternal,String type,
                                   String applicationCode) {
        AjaxResult ajaxResult = new AjaxResult();
        MonitorTypeEnums monitorTypeEnums = MonitorTypeEnums.fromCode(type);
        if (monitorTypeEnums == null) {
            ajaxResult.setStatus(AjaxResult.STATUS_ERROR);
            ajaxResult.setMessage("type参数错误");
            return ajaxResult;
        }

        Context context = null;
        if (!monitorTypeEnums.getCode().equals(MonitorTypeEnums.JVM_REPORT.getCode())) {

            if (clientIp.equals("all")) clientIp = null;
            context = new ToolContext();
            if (monitorTypeEnums.getCode().equals(MonitorTypeEnums.JVM_INFO.getCode())) {

            }
            if (monitorTypeEnums.getCode().equals(MonitorTypeEnums.WEB_URL.getCode())) {
            }

        }
        String content = VelocityTemplateUtil.getContent("vm/" + type + ".vm",context);
        ajaxResult.setData(content);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/report.json")
    public AjaxResult report(String clientIp,String timeInternal,String applicationCode) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String,Object> reportMap = new HashMap<String,Object>();
        JVMGcReportDto jvmGcReportDto = jvmGcService.queryJVMGcReportDto(applicationCode,timeInternal,clientIp);
        JVMMemoryReportDto jvmMemoryReportDto = jvmMemoryService.queryJVMMemoryDto(applicationCode,timeInternal,clientIp);
        JVMThreadReportDto jvmThreadReportDto = jvmThreadService.queryThreadDto(applicationCode,timeInternal,clientIp);
        reportMap.put("headReport",jvmMemoryReportDto.getHeadMemoryDto());
        reportMap.put("nonHeadReport",jvmMemoryReportDto.getNonHeadMemoryDto());
        reportMap.put("jvmMemoryDetailReport",jvmMemoryReportDto.getJvmMemoryDetailDto());
        reportMap.put("threadReport",jvmThreadReportDto.getThreadReportDto());
        reportMap.put("threadCpuRateReport",jvmThreadReportDto.getThreadCpuRateReportDto());
        reportMap.put("gcCount",jvmGcReportDto.getGcCountDto());
        reportMap.put("gcTime",jvmGcReportDto.getGcTimeDto());
        ajaxResult.setData(reportMap);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/sqlReport.json")
    public AjaxResult sqlReport(String clientIp,String timeInternal,String applicationCode) {
        AjaxResult ajaxResult = new AjaxResult();


        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/springMethod.json")
    public AjaxResult springMethod(String clientIp,String timeInternal,String applicationCode) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }
}
