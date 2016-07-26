package com.pacific.service;

import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;

import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public interface SqlService {

    public void saveSqlInfo(String appCode,String clientIp,String hostName,List<SqlInfo> sqlInfoList);
}
