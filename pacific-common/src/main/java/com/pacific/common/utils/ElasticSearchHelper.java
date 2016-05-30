package com.pacific.common.utils;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public String search() {
        return "";
    }


}
