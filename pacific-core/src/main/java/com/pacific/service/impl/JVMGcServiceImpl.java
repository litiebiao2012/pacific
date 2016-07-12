package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.JVMGcDto;
import com.pacific.domain.entity.JVMGc;
import com.pacific.mapper.JVMGcMapper;
import com.pacific.service.JVMGcService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMGcServiceImpl implements JVMGcService {

    public static final Logger logger = LoggerFactory.getLogger(JVMGcServiceImpl.class);

    @Resource
    private JVMGcMapper jvmGcMapper;

    @Override
    public void saveJVMGc(JVMGcDto jvmGcDto) {
        JVMGc jvmGc = new JVMGc();
        try {
            jvmGc.setYoungGcCollectionCount(jvmGcDto.getYoungGCCollectionCount());
            jvmGc.setYoungGcCollectionTime(jvmGcDto.getYoungGCCollectionTime());
            jvmGc.setFullGcCollectionCount(jvmGcDto.getFullGCCollectionCount());
            jvmGc.setFullGcCollectionTime(jvmGcDto.getFullGCCollectionTime());
            jvmGc.setClientIp(jvmGcDto.getClientIp());
            jvmGc.setApplicationCode(jvmGcDto.getAppCode());
            jvmGc.setCreateTime(new Date());
            jvmGc.setUpdateTime(new Date());
            jvmGcMapper.insertSelective(jvmGc);
        } catch (Exception e) {
            logger.error("saveJVMGc error!, e : {}",e);
        }
    }
}
