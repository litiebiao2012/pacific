package com.pacific.service.impl;

import com.pacific.common.*;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.GetUTCTimeUtil;
import com.pacific.common.utils.NamedThreadFactory;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.domain.search.result.LoggerResult;
import com.pacific.service.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
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
                try {
                    loadElasticSearchErrorLog();
                } catch (Exception e) {
                    logger.error("loadElasticSearchErrorLog error !",e);
                }
            }
        },0, Constants.LOAD_ELASTIC_SEARCH_ERROR_LOG_DELAY, TimeUnit.SECONDS);
    }


    /**
     * 读取elasticsearch error级别数据,并存入数据库
     */
    public void loadElasticSearchErrorLog() {
        List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
        if (CollectionUtil.isNotEmpty(applicationList)) {
            for (Application application : applicationList) {
                String applicationCode = application.getApplicationCode();

                ErrorLogRecord errorLogRecord = errorLogRecordService.queryErrorLogRecordInDayByApplicationCode(applicationCode);

                LoggerQuery loggerQuery = new LoggerQuery();
                loggerQuery.setIndex(applicationCode);
                loggerQuery.setLevel("error");
                loggerQuery.setType(Constants.DEFAULT_ELASTIC_SEARCH_LOG_TYPE);
                if (errorLogRecord != null) {
                    //TODO 如果已经加载过,则只加载数据库中最新的数据以后的 搜索引擎上的数据,增量抓取
                    loggerQuery.setBeginDate(new Date(errorLogRecord.getElasticsearchLogCreateTime()));
                }
                List<LoggerResult> returnList = new LinkedList<LoggerResult>();
                queryLoggerResult(loggerQuery,returnList,0);

                errorLogRecordService.batchSaveErrorLogRecord(returnList);


            }
        }
    }

    /**
     * 递归查询,直到查询到空为止
     * @param loggerQuery
     * @param returnList
     */
    private void queryLoggerResult(LoggerQuery loggerQuery,List<LoggerResult> returnList,int index) {
        List<LoggerResult> loggerResultList = elasticSearchHelper.searchLog(loggerQuery);
        if (CollectionUtil.isEmpty(loggerResultList)) return;

        Collections.sort(loggerResultList, new Comparator<LoggerResult>() {
            @Override
            public int compare(LoggerResult o1, LoggerResult o2) {
                if (o1.getTimestamp() < o2.getTimestamp())
                    return 1;
                else if (o1.getTimestamp() > o2.getTimestamp())
                    return -1;
                else
                    return 0;

            }
        });
        returnList.addAll(loggerResultList);
        loggerQuery.setBeginDate(new Date(loggerResultList.get(0).getTimestamp()));
        //TODO 防止数据太多,无限递归,导致应用内存溢出
        if (index == 5) return;
        index++;
        queryLoggerResult(loggerQuery,returnList,index);
    }

}
