package com.pacific.service;

import com.pacific.common.exception.PacificException;
import com.pacific.common.json.FastJson;
import com.pacific.domain.search.query.LoggerQuery;
import com.pacific.domain.search.result.LoggerResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchHelper {

    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchHelper.class);

    public static final String ELASTIC_SEARCH_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";


    private String clusterName;
    private String ip;
    private int port;

    private Settings settings = Settings
            .settingsBuilder()
            .put("cluster.name",clusterName)
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

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("level",loggerQuery.getLevel()));
        if (loggerQuery.getBeginDate() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("@timestamp").gt(loggerQuery.getBeginDate().getTime()));
        }

        SearchResponse response= client.prepareSearch(loggerQuery.getIndex())//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(loggerQuery.getType())//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(boolQueryBuilder)//在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(loggerQuery.getStart())
                .setSize(loggerQuery.getSize())
                .setExplain(true)
                .execute()
                .actionGet();

        SearchHits searchHits = response.getHits();

        List<LoggerResult> loggerResultList = new LinkedList<LoggerResult>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ELASTIC_SEARCH_DATE_FORMAT);
        if (searchHits.totalHits() > 0) {
            for (SearchHit hit : searchHits) {
                try {
                    Map<String,Object> sourceMap = hit.getSource();
                    LoggerResult loggerResult = new LoggerResult();

                    loggerResult.setIndex((String)sourceMap.get("_index"));
                    loggerResult.setType((String)sourceMap.get("_type"));
                    loggerResult.setId((String)sourceMap.get("_id"));
                    loggerResult.setScore((Integer)sourceMap.get("_score"));
                    String timestamp = (String)sourceMap.get("@timestamp");
                    loggerResult.setTimestamp(simpleDateFormat.parse(timestamp));

                    loggerResult.setVersion((Integer)sourceMap.get("@version"));
                    loggerResult.setMessage((String)sourceMap.get("message"));
                    loggerResult.setLoggerName((String)sourceMap.get("logger_name"));
                    loggerResult.setThreadName((String)sourceMap.get("thread_name"));
                    loggerResult.setLevel((String)sourceMap.get("level"));
                    loggerResult.setLevelValue((Integer)sourceMap.get("level_value"));
                    loggerResult.setStackTrace((String)sourceMap.get("stack_trace"));
                    loggerResult.setHostName((String)sourceMap.get("hostname"));
                    loggerResult.setHost((String)sourceMap.get("host"));
                    loggerResult.setPath((String)sourceMap.get("path"));
                } catch (ParseException e) {
                    logger.error("parse error , e : {}",e);
                }
            }
        }
        return null;
    }


}
