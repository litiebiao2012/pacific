package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.SpringMethodInfo;
import com.pacific.domain.entity.SpringMethod;
import com.pacific.mapper.SpringMethodMapper;
import com.pacific.service.SpringMethodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/7/13.
 */
public class SpringMethodServiceImpl implements SpringMethodService {
    @Resource
    private SpringMethodMapper springMethodMapper;

    @Override
    public void saveSpringMethodInfo(String appCode, String clientIp,List<SpringMethodInfo> springMethodInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(springMethodInfoList);

        List<SpringMethod> springMethodList = new ArrayList<SpringMethod>();
        for (SpringMethodInfo springMethodInfo : springMethodInfoList) {
            SpringMethod springMethod = new SpringMethod();
            springMethod.setCreateTime(new Date());
            springMethod.setUpdateTime(new Date());
            springMethod.setApplicationCode(appCode);
            springMethod.setClientIp(clientIp);
            springMethod.setClassName(springMethodInfo.getClassName());
            springMethod.setMethod(springMethodInfo.getMethod());
            springMethod.setCount(springMethodInfo.getCount());
            springMethod.setConcurrentMax((long)springMethodInfo.getConcurrentMax());
            springMethod.setNanoMax(springMethodInfo.getNanoMax());
            springMethod.setNanoTotal(springMethodInfo.getNanoTotal());

            if (StringUtils.isNotEmpty(springMethodInfo.getLastErrorMsg())) {
                springMethod.setIsError("y");
            } else {
                springMethod.setIsError("n");
            }
            springMethod.setErrorMsg(springMethodInfo.getLastErrorMsg());
            springMethod.setErrorStackTrace(springMethodInfo.getLastErrorStackTrace());
            springMethod.setErrorTime(springMethodInfo.getLastErrorTime());

            springMethodList.add(springMethod);
        }
        springMethodMapper.batchSaveSpringMethod(springMethodList);
    }
}
