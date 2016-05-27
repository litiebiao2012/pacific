package com.pacific.common.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 返回json时使用, 支持json , jsonp, 需配合RequestContext 使用, 注意
 * Created by Administrator on 2016/1/29.
 */
public class JsonCommonRender {
    public static final Logger logger = LoggerFactory.getLogger(JsonCommonRender.class);

    private static       String      CALLBACK_REGEXP     = "[^0-9a-zA-Z_\\.]";
    private static       int         CALLBACK_MAX_LENGTH = 128;
    private static Pattern PATTERN             = Pattern.compile(CALLBACK_REGEXP);
    private static       String      JSON_HEADER_APPEND  = "\r\n\r\n";
    private static       String      CHAR_SET            = "UTF-8";
    private static ValueFilter filter              = new JsonStringValueFilter();

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer(DEFAULT_DATE_FORMAT));
    }

    public static String getJsonResult(Object obj) {
        String callback = RequestContext.getStr("callback");
        RequestContext.getResponse().setCharacterEncoding(CHAR_SET);
        RequestContext.getResponse().setHeader("Cache-Control", "no-cache");
        String text = "";
        if (StringUtils.isNotBlank(callback)) {
            callback = StringEscapeUtils.escapeHtml4(callbackFilter(callback));
            RequestContext.getResponse().setHeader("Content-Type", "application/javascript");
            text = JSON_HEADER_APPEND + callback + "(" + getJson(obj) + ");";
        } else {
            RequestContext.getResponse().setHeader("Content-Type", "application/json");
            text = JSON_HEADER_APPEND + getJson(obj);
        }
        return text;
    }

    public static String callbackFilter(String callback) {
        if (StringUtils.isEmpty(callback)) {
            return StringUtils.EMPTY;
        }

        String filterCallback = callback;

        if (StringUtils.length(filterCallback) > CALLBACK_MAX_LENGTH) {
            filterCallback = filterCallback.substring(0, CALLBACK_MAX_LENGTH);
        }

        Matcher m = PATTERN.matcher(filterCallback);

        filterCallback = m.replaceAll("");

        if (!StringUtils.equals(callback, filterCallback)) {
            logger.error("callback was filter, callback:" + callback + ",filterCallcack:" + filterCallback);
        }

        return filterCallback;
    }

    private static String getJson(Object obj) {
        boolean isEscape = RequestContext.getBoolean("isEscape", false);
        String result = "";
        if (isEscape) {
            result = JSON.toJSONString(obj,mapping,filter,getFeatures());
        } else {
            result =  JSON.toJSONString(obj,mapping,getFeatures());
        }
        logger.info("响应seq : {} ,响应内容 : {}",RequestContext.getSeq(),result);
        return result;
    }

    public static SerializerFeature[] getFeatures() {
        return new SerializerFeature[0];
    }
}
