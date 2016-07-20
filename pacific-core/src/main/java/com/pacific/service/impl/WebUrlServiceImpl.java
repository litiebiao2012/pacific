package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.WebUrlInfo;
import com.pacific.domain.entity.WebUrl;
import com.pacific.mapper.WebUrlMapper;
import com.pacific.service.WebUrlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public class WebUrlServiceImpl implements WebUrlService {

    @Resource
    private WebUrlMapper webUrlMapper;


    public void saveWebUrlInfo(String appCode,String clientIp,List<WebUrlInfo> webUrlInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(webUrlInfoList);

        List<WebUrl> webUrlList = new ArrayList<WebUrl>();
        for (WebUrlInfo webUrlInfo : webUrlInfoList) {
            WebUrl webUrl = new WebUrl();
            webUrl.setCreateTime(new Date());
            webUrl.setUpdateTime(new Date());
            webUrl.setApplicationCode(appCode);
            webUrl.setClientIp(clientIp);
            webUrl.setUrl(webUrlInfo.getUrl());
            webUrl.setCount(webUrlInfo.getCount());
            webUrl.setConcurrentMax(webUrlInfo.getConcurrentMax());
            webUrl.setNanoTotal(webUrlInfo.getNanoTotal());
            webUrl.setNanoMax(webUrlInfo.getNanoMax());
            webUrl.setConcurrentMax(webUrlInfo.getConcurrentMax());

            if (StringUtils.isNotEmpty(webUrlInfo.getLastErrorMsg())) {
                webUrl.setIsError("y");
                webUrl.setErrorMsg(webUrlInfo.getLastErrorMsg());
                webUrl.setErrorTime(webUrlInfo.getLastErrorTime());
            } else {
                webUrl.setIsError("n");
            }
            webUrlList.add(webUrl);
        }
        webUrlMapper.batchSaveWebUrl(webUrlList);
    }
}
