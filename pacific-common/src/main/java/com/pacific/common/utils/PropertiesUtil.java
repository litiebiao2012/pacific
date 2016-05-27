package com.pacific.common.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Fe on 16/4/7.
 */
public class PropertiesUtil {

    public static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static Properties getProperties(String propertiesName) {
        InputStream inputStream = null;
        try {
            inputStream = PropertiesUtil.class.getResourceAsStream(propertiesName);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException("getProperties error!");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
