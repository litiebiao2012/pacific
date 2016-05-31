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
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchHelper {

    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchHelper.class);

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

    public LoggerResult searchNewErrorLog(LoggerQuery loggerQuery) {
        Assert.notNull(loggerQuery);
        logger.info("searchNewErrorLog param : {}", FastJson.toJson(loggerQuery));
        SearchResponse response= client.prepareSearch(loggerQuery.getIndex())//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(loggerQuery.getType())//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(QueryBuilders.boolQuery().
                        must(QueryBuilders.matchQuery("level", loggerQuery.getLevel())).
                        must(QueryBuilders.rangeQuery("@timestamp").gt(loggerQuery.getBeginDate().getTime())))//在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(0)
                .setSize(10000)
                .setExplain(true)
                .execute()
                .actionGet();
        return null;
    }


}
