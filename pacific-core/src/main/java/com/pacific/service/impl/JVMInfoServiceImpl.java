package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.JVMInfoDto;
import com.pacific.domain.entity.JVMInfo;
import com.pacific.domain.entity.JVMMemory;
import com.pacific.mapper.JVMInfoMapper;
import com.pacific.service.JVMInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Fe on 16/7/12.
 */
public class JVMInfoServiceImpl implements JVMInfoService {

    public static final Logger logger = LoggerFactory.getLogger(JVMInfoServiceImpl.class);
    @Resource
    private JVMInfoMapper jvmInfoMapper;

    @Override
    public void saveJVMInfo(JVMInfoDto jvmInfoDto) {
        JVMInfo jvmInfo = new JVMInfo();
        try {
            String appCode = jvmInfoDto.getAppCode();
            String clientIp = jvmInfoDto.getClientIp();

            JVMInfo ji = jvmInfoMapper.selectByParam(appCode,clientIp);
            if (ji == null) {
                BeanUtils.copyProperties(jvmInfo,jvmInfoDto);
                jvmInfo.setApplicationCode(jvmInfoDto.getAppCode());
                jvmInfo.setCreateTime(new Date());
                jvmInfo.setUpdateTime(new Date());
                jvmInfoMapper.insertSelective(jvmInfo);
            } else {
                BeanUtils.copyProperties(ji,jvmInfoDto);
                jvmInfoMapper.updateByPrimaryKeySelective(jvmInfo);
            }
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }
}
