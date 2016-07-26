package com.pacific.open.api.controller;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.WebUrlDto;
import com.pacific.domain.dto.jvm.WebUrlInfo;
import com.pacific.service.WebUrlService;
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
@RequestMapping("/url")
public class WebUrlController {

    @Resource
    private WebUrlService webUrlService;

    @ResponseBody
    @RequestMapping("/saveWebUrlInfo.json")
    public AjaxResult saveWebUrlInfo(WebUrlDto webUrlDto) {
        String jsonData = webUrlDto.getData();
        if (StringUtils.isNotEmpty(jsonData)) {
            List<WebUrlInfo> webUrlInfoList = FastJson.jsonToList(jsonData, WebUrlInfo.class);
            webUrlService.saveWebUrlInfo(webUrlDto.getAppCode(),webUrlDto.getClientIp(),webUrlDto.getHostName(),webUrlInfoList);
        }
        return new AjaxResult();
    }
}
