package com.pacific.service;

import com.pacific.domain.dto.jvm.SpringMethodInfo;
import com.pacific.domain.entity.SpringMethod;

import java.util.List;

/**
 * Created by Fe on 16/7/13.
 */
public interface SpringMethodService {

    public void saveSpringMethodInfo(String appCode,String clientIp,List<SpringMethodInfo> springMethodInfoList);
}
