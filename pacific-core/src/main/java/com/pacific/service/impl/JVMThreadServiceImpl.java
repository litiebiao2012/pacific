package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.JVMThreadDto;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.domain.entity.JVMThread;
import com.pacific.mapper.JVMThreadMapper;
import com.pacific.service.JVMThreadService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMThreadServiceImpl implements JVMThreadService {

    public static final Logger logger = LoggerFactory.getLogger(JVMMemoryServiceImpl.class);

    @Resource
    private JVMThreadMapper jvmThreadMapper;

    @Override
    public void saveJVMThread(JVMThreadDto jvmThreadDto) {
        JVMThread jvmThread = new JVMThread();
        try {
            BeanUtils.copyProperties(jvmThread,jvmThreadDto);
            jvmThread.setApplicationCode(jvmThreadDto.getAppCode());
            jvmThread.setCreateTime(new Date());
            jvmThread.setUpdateTime(new Date());
            jvmThreadMapper.insertSelective(jvmThread);
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }
}
