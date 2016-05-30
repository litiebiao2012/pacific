package com.pacific.service.impl;

import com.pacific.service.ElasticSearchService;
import org.elasticsearch.bootstrap.Elasticsearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchServiceImpl implements ElasticSearchService {

    public static final Integer BACK_THREAD_SLEEP_TIME = 1000;

    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    public ElasticSearchServiceImpl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(BACK_THREAD_SLEEP_TIME);
                        loadElasticSearchErrorLog();
                    } catch (Exception e) {
                        logger.error("loadElasticSearchErrorLog error ,e : {}",e);
                    }
                }
            }
        }).start();
    }


    /**
     * 读取elasticsearch error级别数据,并存入数据库
     */
    public void loadElasticSearchErrorLog() {

    }
}
