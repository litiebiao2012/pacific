package com.pacific.service.impl;

import com.pacific.common.*;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.NamedThreadFactory;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.domain.search.result.LoggerResult;
import com.pacific.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchServiceImpl implements ElasticSearchService {

    public static final Integer BACK_THREAD_SLEEP_TIME = 1000;

    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1,new NamedThreadFactory("load-elasticsearch-error-log"));

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ErrorLogRecordService errorLogRecordService;

    @Resource
    private ElasticSearchHelper elasticSearchHelper;

    @PostConstruct
    public void init() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                loadElasticSearchErrorLog();
            }
        },0, Constants.LOAD_ELASTIC_SEARCH_ERROR_LOG_DELAY, TimeUnit.SECONDS);
    }


    /**
     * 读取elasticsearch error级别数据,并存入数据库
     */
    public void loadElasticSearchErrorLog() {
        List<Application> applicationList = applicationService.queryAllApplication();
        if (CollectionUtil.isNotEmpty(applicationList)) {
            for (Application application : applicationList) {
                String applicationCode = application.getApplicationCode();

                ErrorLogRecord errorLogRecord = errorLogRecordService.queryErrorLogRecordInDayByApplicationCode(applicationCode);

                LoggerQuery loggerQuery = new LoggerQuery();
                loggerQuery.setIndex(applicationCode);
                loggerQuery.setLevel("ERROR");
                loggerQuery.setType(Constants.DEFAULT_ELASTIC_SEARCH_LOG_TYPE);
                if (errorLogRecord != null) {
                    //TODO 如果已经加载过,则只加载数据库中最新的数据以后的 搜索引擎上的数据,增量抓取
                    loggerQuery.setBeginDate(errorLogRecord.getElasticsearchLogCreateTime());
                }
                List<LoggerResult> returnList = new LinkedList<LoggerResult>();
                queryLoggerResult(loggerQuery,returnList);

                errorLogRecordService.batchSaveErrorLogRecord(returnList);


            }
        }
    }

    /**
     * 递归查询,直到查询到空为止
     * @param loggerQuery
     * @param returnList
     */
    private void queryLoggerResult(LoggerQuery loggerQuery,List<LoggerResult> returnList) {
        List<LoggerResult> loggerResultList = elasticSearchHelper.searchNewErrorLog(loggerQuery);
        if (CollectionUtil.isEmpty(loggerResultList)) return;

        Collections.sort(loggerResultList, new Comparator<LoggerResult>() {
            @Override
            public int compare(LoggerResult o1, LoggerResult o2) {
                if (o1.getTimestamp().getTime() > o2.getTimestamp().getTime())
                    return -1;
                else if (o1.getTimestamp().getTime() == o2.getTimestamp().getTime())
                    return 0;
                else
                    return 1;

            }
        });
        returnList.addAll(loggerResultList);
        loggerQuery.setBeginDate(loggerResultList.get(0).getTimestamp());
        queryLoggerResult(loggerQuery,returnList);
    }
}
