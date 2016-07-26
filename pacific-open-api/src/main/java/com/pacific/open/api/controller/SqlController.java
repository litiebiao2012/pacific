package com.pacific.open.api.controller;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;
import com.pacific.service.SqlService;
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
@RequestMapping("/sql")
public class SqlController {

    @Resource
    private SqlService sqlService;

    @ResponseBody
    @RequestMapping("/saveSql.json")
    public AjaxResult saveSpringMethodInfo(SqlDto sqlDto) {
        String jsonData = sqlDto.getData();
        if (StringUtils.isNotEmpty(jsonData)) {
            List<SqlInfo> sqlInfoList = FastJson.jsonToList(jsonData, SqlInfo.class);
            sqlService.saveSqlInfo(sqlDto.getAppCode(),sqlDto.getClientIp(),sqlDto.getHostName(),sqlInfoList);
        }
        return new AjaxResult();
    }
}
