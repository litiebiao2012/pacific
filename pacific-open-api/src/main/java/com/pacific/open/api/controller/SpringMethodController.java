package com.pacific.open.api.controller;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.SpringMethodDto;
import com.pacific.domain.dto.jvm.SpringMethodInfo;
import com.pacific.service.SpringMethodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fe on 16/7/8.
 */
@Controller
@RequestMapping("/sm")
public class SpringMethodController {
    @Resource
    private SpringMethodService springMethodService;

    @ResponseBody
    @RequestMapping("/saveSmInfo.json")
    public AjaxResult saveSpringMethodInfo(SpringMethodDto springMethodDto) {
        String jsonData = springMethodDto.getData();
        if (StringUtils.isNotEmpty(jsonData)) {
            List<SpringMethodInfo> springMethodInfoList = FastJson.jsonToList(jsonData, SpringMethodInfo.class);
            springMethodService.saveSpringMethodInfo(springMethodDto.getAppCode(),springMethodDto.getClientIp(),springMethodInfoList);
        }
        return new AjaxResult();
    }
}
