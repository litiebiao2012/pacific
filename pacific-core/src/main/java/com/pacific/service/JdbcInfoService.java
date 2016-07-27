package com.pacific.service;

import com.pacific.domain.dto.jvm.JdbcInfoDetail;
import com.pacific.domain.dto.jvm.SpringMethodInfo;

import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public interface JdbcInfoService {

    public void saveJdbcInfo(String appCode,String clientIp,String hostName,List<JdbcInfoDetail> jdbcInfoDetailList);
}
