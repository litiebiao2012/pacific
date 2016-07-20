package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.JdbcInfoDetail;
import com.pacific.domain.entity.JdbcInfo;
import com.pacific.mapper.JdbcInfoMapper;
import com.pacific.service.JdbcInfoService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public class JdbcInfoServiceImpl implements JdbcInfoService {

    @Resource
    private JdbcInfoMapper jdbcInfoMapper;

    public void saveJdbcInfo(String appCode,String clientIp,List<JdbcInfoDetail> jdbcInfoDetailList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(jdbcInfoDetailList);

        for (JdbcInfoDetail jdbcInfoDetail : jdbcInfoDetailList) {
            JdbcInfo jdbcInfo = jdbcInfoMapper.queryJdbcInfoByParam(appCode,clientIp,jdbcInfoDetail.getUrl());
            if (jdbcInfo == null) {
                jdbcInfo = buildJdbcInfo(appCode,clientIp,jdbcInfoDetail,null);
                jdbcInfoMapper.insert(jdbcInfo);
            } else {
                jdbcInfo = buildJdbcInfo(appCode,clientIp,jdbcInfoDetail,jdbcInfo.getId());
                jdbcInfoMapper.updateByPrimaryKeySelective(jdbcInfo);
            }
        }
    }

    private JdbcInfo buildJdbcInfo(String appCode,String clientIp,JdbcInfoDetail jdbcInfoDetail,Long id) {
        JdbcInfo jdbcInfo = new JdbcInfo();

        if (id == null) {
            jdbcInfo.setCreateTime(new Date());
        }
        jdbcInfo.setUpdateTime(new Date());
        jdbcInfo.setApplicationCode(appCode);
        jdbcInfo.setClientIp(clientIp);
        jdbcInfo.setUrl(jdbcInfoDetail.getUrl());
        jdbcInfo.setDbType(jdbcInfoDetail.getDbType());
        jdbcInfo.setDriverClassName(jdbcInfoDetail.getDriverClassName());
        jdbcInfo.setErrorCount(jdbcInfoDetail.getErrorCount());
        jdbcInfo.setMaxActive(jdbcInfoDetail.getMaxActive());
        jdbcInfo.setMinIdle(jdbcInfoDetail.getMinIdle());
        jdbcInfo.setPoolingCount(jdbcInfoDetail.getPoolingCount());
        jdbcInfo.setUserName(jdbcInfoDetail.getUserName());
        jdbcInfo.setName(jdbcInfoDetail.getName());

        if (id != null) jdbcInfo.setId(id);

        return jdbcInfo;
    }
}
