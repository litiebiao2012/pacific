package com.pacific.service.impl;

import com.pacific.domain.dto.jvm.SqlDto;
import com.pacific.domain.dto.jvm.SqlInfo;
import com.pacific.domain.entity.Sql;
import com.pacific.mapper.SqlMapper;
import com.pacific.service.SqlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/7/20.
 */
public class SqlServiceImpl implements SqlService {

    @Resource
    private SqlMapper sqlMapper;

    public void saveSqlInfo(String appCode,String clientIp,List<SqlInfo> sqlInfoList) {
        Assert.notNull(appCode);
        Assert.notNull(clientIp);
        Assert.notNull(sqlInfoList);

        List<Sql> sqlList = new ArrayList<Sql>();
        for (SqlInfo sqlInfo : sqlInfoList) {
            Sql sql = new Sql();
            sql.setCreateTime(new Date());
            sql.setUpdateTime(new Date());
            sql.setApplicationCode(appCode);
            sql.setClientIp(clientIp);
            sql.setConcurrentMax(sqlInfo.getConcurrentMax());
            sql.setCount(sqlInfo.getCount());
            sql.setEffectedRowCount(sqlInfo.getEffectedRowCount());
            sql.setEffectedRowCountMax(sqlInfo.getEffectedRowCountMax());
            sql.setFetchRowCount(sqlInfo.getFetchRowCount());
            sql.setFetchRowCountMax(sqlInfo.getFetchRowCountMax());
            sql.setMaxTime(sqlInfo.getMaxTime());
            sql.setSqlText(sqlInfo.getSql());
            sql.setSqlHash(sqlInfo.getSqlHash());
            sql.setTotalTime(sqlInfo.getTotalTime());
            sql.setUrl(sqlInfo.getUrl());

            if (StringUtils.isNotEmpty(sqlInfo.getLastErrorMsg())) {
                sql.setIsError("y");
                sql.setErrorMsg(sqlInfo.getLastErrorMsg());
                sql.setErrorTime(sqlInfo.getLastErrorTime());
            } else {
                sql.setIsError("n");
            }
            sqlList.add(sql);
        }
        sqlMapper.batchSaveSql(sqlList);
    }
}
