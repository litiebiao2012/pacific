package com.pacific.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Fe on 16/5/27.
 */
public class Constants {
    public static final String COOKIE_CRYPTO_PASS = "2A8B3#E@0(#%!*5(_#`~^*)@%$@$!$^%*CA@#%DE8#258$(@&%46F";

    private static Logger logger= LoggerFactory.getLogger(Constants.class);

    public static final String DEFAULT_CHARSET    = "UTF-8";

    public static ResourceBundle resourceBundle =  ResourceBundle.getBundle("pacific", Locale.getDefault());
    public static boolean NEED_DEPEND_COOKIE = Boolean.parseBoolean(resourceBundle.getString("needDependCookie"));
    public static String BASE_DOMAIN = resourceBundle.getString("base.domain");

    public static String APP_NAME = resourceBundle.getString("appName");

    /**
     * session失效时间 分钟
     */
    public static final long TOKEN_EXPIRE_TIME = 300;

}
