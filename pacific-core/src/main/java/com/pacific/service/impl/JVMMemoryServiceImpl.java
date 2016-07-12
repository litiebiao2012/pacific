package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.JVMMemoryDto;
import com.pacific.domain.entity.JVMGc;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.mapper.JVMMemoryMapper;
import com.pacific.service.JVMMemoryService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMMemoryServiceImpl implements JVMMemoryService{
    public static final Logger logger = LoggerFactory.getLogger(JVMMemoryServiceImpl.class);

    @Resource
    private JVMMemoryMapper jvmMemoryMapper;

    @Override
    public void saveJVMMemory(JVMMemoryDto jvmMemoryDto) {
        JVMMemory jvmMemory = new JVMMemory();
        try {
            BeanUtils.copyProperties(jvmMemory,jvmMemoryDto);
            jvmMemory.setApplicationCode(jvmMemoryDto.getAppCode());
            jvmMemory.setCreateTime(new Date());
            jvmMemory.setUpdateTime(new Date());
            jvmMemoryMapper.insertSelective(jvmMemory);
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }
}
