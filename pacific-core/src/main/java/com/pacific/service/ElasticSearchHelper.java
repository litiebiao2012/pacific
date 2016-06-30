package com.pacific.service;

import com.pacific.common.Constants;
import com.pacific.common.exception.PacificException;
import com.pacific.common.json.FastJson;
import com.pacific.common.utils.DateUtil;
import com.pacific.common.utils.GetUTCTimeUtil;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.domain.search.result.LoggerResult;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchHelper {

    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchHelper.class);

    public static final String ELASTIC_SEARCH_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss";


    private String clusterName;
    private String ip;
    private int port;

    private Settings settings = Settings
            .settingsBuilder()
            .put("cluster.name", Constants.ELASTICSEARCH_CLUSTER_NAME)
            .put("client.transport.sniff", true)
            .build();

    private Client client;

    private synchronized Client getClient() {
        if (client == null) {
            try {
                InetAddress inetAddress = InetAddress.getByName(ip);
                client = TransportClient.builder().settings(settings).
                        build().addTransportAddress(new InetSocketTransportAddress(inetAddress, port));
            } catch (UnknownHostException e) {
                logger.error("InetAddress.getByName error, e : {}",e);
            }
        }
        return client;
    }

    public List<LoggerResult> searchNewErrorLog(LoggerQuery loggerQuery) {
        Assert.notNull(loggerQuery);
        logger.info("searchNewErrorLog param : {}", FastJson.toJson(loggerQuery));

        BoolQueryBuilder boolQueryBuilder = buildBoolQueryBuilder(loggerQuery);

        SearchResponse response= getClient().prepareSearch(loggerQuery.getIndex())//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(loggerQuery.getType())//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(boolQueryBuilder)//在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(loggerQuery.getStartRow())
                .setSize(loggerQuery.getPageSize())
                .setExplain(true)
                .execute()
                .actionGet();


        SearchHits searchHits = response.getHits();

        if (logger.isDebugEnabled()) {
            logger.debug("searchHist result : {}",FastJson.toJson(searchHits));
        }

        List<LoggerResult> loggerResultList = new LinkedList<LoggerResult>();

        if (searchHits.totalHits() > 0) {
            for (SearchHit hit : searchHits) {
                try {
                    Map<String,Object> sourceMap = hit.getSource();
                    LoggerResult loggerResult = new LoggerResult();

                    loggerResult.setIndex(hit.getIndex());
                    loggerResult.setType(hit.getType());
                    loggerResult.setId(hit.getId());
                    loggerResult.setScore(0);
                    String timestamp = (String)sourceMap.get("@timestamp");
                    loggerResult.setTimestamp(GetUTCTimeUtil.getLocalTimeFromUTC(timestamp));
                    loggerResult.setVersion((Integer)sourceMap.get("@version"));
                    loggerResult.setMessage((String)sourceMap.get("message"));
                    loggerResult.setLoggerName((String)sourceMap.get("logger_name"));
                    loggerResult.setThreadName((String)sourceMap.get("thread_name"));
                    loggerResult.setLevel((String)sourceMap.get("level"));
                    loggerResult.setLevelValue((Integer)sourceMap.get("level_value"));
                    loggerResult.setStackTrace((String)sourceMap.get("stack_trace"));
                    loggerResult.setHostName((String)sourceMap.get("host"));
                    loggerResult.setHost((String)sourceMap.get("host"));
                    loggerResult.setPath((String)sourceMap.get("path"));

                    loggerResultList.add(loggerResult);
                } catch (Exception e) {
                    logger.error("parse error , e : {}",e);
                }
            }
        }
        return loggerResultList;
    }

    public void createIndexResponse(String indexName){
        getClient().admin().indices().prepareCreate(indexName).execute()
                    .actionGet();
    }

    public Long queryTotalLog(String applicationCode,LoggerQuery loggerQuery) {
        return queryTotalLog(new String[]{applicationCode},loggerQuery);
    }

    public Long queryTotalLog(String[] applicationCodes,LoggerQuery loggerQuery) {

        BoolQueryBuilder boolQueryBuilder = buildBoolQueryBuilder(loggerQuery);

        SearchResponse response= getClient().prepareSearch(applicationCodes)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(Constants.DEFAULT_ELASTIC_SEARCH_LOG_TYPE)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(boolQueryBuilder)//在这里"message"是要查询的field,"Accept"是要查询的内容
                .setExplain(true)
                .execute()
                .actionGet();
        return response.getHits().getTotalHits();
    }


    private BoolQueryBuilder buildBoolQueryBuilder(LoggerQuery loggerQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(StringUtils.isNotEmpty(loggerQuery.getLevel())) boolQueryBuilder.must(QueryBuilders.matchQuery("level",loggerQuery.getLevel()));

        if (StringUtils.isNotEmpty(loggerQuery.getMessage())) {
            boolQueryBuilder.must(QueryBuilders.moreLikeThisQuery("message").addLikeText(loggerQuery.getMessage()));
        }

        if (loggerQuery.getBeginDate() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("@timestamp").gte(loggerQuery.getBeginDate()));
        }

        if (loggerQuery.getEndDate() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("@timestamp").lt(loggerQuery.getEndDate()));
        }
        return boolQueryBuilder;
    }



    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
