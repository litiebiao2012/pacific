package com.pacific.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Fe on 16/5/27.
 */
public class Constants {

    public static final String DEFAULT_ELASTIC_SEARCH_LOG_TYPE = "pacific_log_stash_log";

    public static final String ELASTICSEARCH_CLUSTER_NAME = "elasticsearch";

    public static final String COOKIE_CRYPTO_PASS = "2A8B3#E@0(#%!*5(_#`~^*)@%$@$!$^%*CA@#%DE8#258$(@&%46F";

    private static Logger logger= LoggerFactory.getLogger(Constants.class);

    public static final String DEFAULT_CHARSET    = "UTF-8";

    public static ResourceBundle resourceBundle =  ResourceBundle.getBundle("pacific", Locale.getDefault());
    public static boolean NEED_DEPEND_COOKIE = Boolean.parseBoolean(resourceBundle.getString("needDependCookie"));
    public static String BASE_DOMAIN = resourceBundle.getString("base.domain");

    public static String APP_NAME = resourceBundle.getString("appName");

    public static String SESSION_STORE_TYPE = resourceBundle.getString("sessionStoreType");

    public static final Integer  ALARM_ERROR_LOG_SCHEDULE_DELAY = Integer.parseInt(resourceBundle.getString("alarmErrorLogScheduleDelay"));

    public static final Integer  LOAD_ELASTIC_SEARCH_ERROR_LOG_DELAY = Integer.parseInt(resourceBundle.getString("loadElasticsearchErrorLogDelay"));
    /**
     * session失效时间 分钟
     */
    public static final long TOKEN_EXPIRE_TIME = 300;


}
