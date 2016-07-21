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
import java.util.List;

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

            List<JVMInfo> jvmInfoList = jvmInfoMapper.selectByParam(appCode,clientIp);
            if (jvmInfoList == null || jvmInfoList.size() == 0) {
                BeanUtils.copyProperties(jvmInfo,jvmInfoDto);
                jvmInfo.setApplicationCode(jvmInfoDto.getAppCode());
                jvmInfo.setCreateTime(new Date());
                jvmInfo.setUpdateTime(new Date());

                jvmInfo.setHostName(jvmInfoDto.getHostname());
                jvmInfo.setJvmStartTime(jvmInfoDto.getStartTime());
                jvmInfo.setPid(jvmInfoDto.getpID());
                jvmInfo.setOsName(jvmInfoDto.getoSName());
                jvmInfo.setJvm(jvmInfoDto.getjVM());
                jvmInfoMapper.insertSelective(jvmInfo);
            } else {
                JVMInfo ji = jvmInfoList.get(0);
                BeanUtils.copyProperties(ji,jvmInfoDto);
                ji.setHostName(jvmInfoDto.getHostname());
                ji.setJvmStartTime(jvmInfoDto.getStartTime());
                ji.setPid(jvmInfoDto.getpID());
                ji.setOsName(jvmInfoDto.getoSName());
                ji.setJvm(jvmInfoDto.getjVM());
                ji.setUpdateTime(new Date());
                jvmInfoMapper.updateByPrimaryKeySelective(ji);
            }
        } catch (Exception e) {
            logger.error("saveJVMMemory error!, e : {}",e);
        }
    }
}
