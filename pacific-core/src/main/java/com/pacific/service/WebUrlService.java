package com.pacific.service;

import com.pacific.domain.dto.jvm.WebUrlInfo;

import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public interface WebUrlService {

    public void saveWebUrlInfo(String appCode,String clientIp,List<WebUrlInfo> webUrlInfoList);
}
