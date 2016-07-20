package com.pacific.open.api.controller;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.JdbcInfoDetail;
import com.pacific.domain.dto.jvm.JdbcInfoDto;
import com.pacific.domain.entity.JdbcInfo;
import com.pacific.service.JdbcInfoService;
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
@RequestMapping("/ds")
public class DruidDataSourceController {

    @Resource
    private JdbcInfoService jdbcInfoService;

    @ResponseBody
    @RequestMapping("/saveDruidInfo.json")
    public AjaxResult saveWebUrlInfo(JdbcInfoDto jdbcInfoDto) {
        String jsonData = jdbcInfoDto.getData();
        if (StringUtils.isNotEmpty(jsonData)) {
            List<JdbcInfoDetail> jdbcInfoDetailList = FastJson.jsonToList(jsonData, JdbcInfoDetail.class);
            jdbcInfoService.saveJdbcInfo(jdbcInfoDto.getAppCode(),jdbcInfoDto.getClientIp(),jdbcInfoDetailList);
        }
        return new AjaxResult();
    }
}
