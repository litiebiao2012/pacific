package com.pacific.open.api.controller;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.SpringMethodDto;
import com.pacific.domain.dto.jvm.SpringMethodInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Fe on 16/7/8.
 */
@Controller
@RequestMapping("/sm")
public class SpringMethodController {

    @ResponseBody
    @RequestMapping("/saveSmInfo.json")
    public AjaxResult saveSpringMethodInfo(SpringMethodDto springMethodDto) {

        String jsonData = springMethodDto.getData();
        List<SpringMethodInfo> springMethodInfoList = FastJson.jsonToList(jsonData, SpringMethodInfo.class);

        return new AjaxResult();
    }
}
