package com.pacific.common.utils;

import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import sun.org.mozilla.javascript.internal.EcmaError;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Fe on 16/6/13.
 */
public class GetUTCTimeUtil {


    public static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss";

    /**
     * 将UTC时间转换为东八区时间
     * @param UTCTime
     * @return
     */
    public static Long getLocalTimeFromUTC(String UTCTime){
        DateTimeZone tz = DateTimeZone.UTC;
        LocalDateTime date =  LocalDateTime.parse(UTCTime.substring(0,UTCTime.length() - 1));
        try {
            return date.toDateTime(tz).toDateTime(DateTimeZone.getDefault()).toDate().getTime();
        } catch (Exception e) {
            throw new RuntimeException("parse error!");
        }
    }

    public static void main(String args[]) throws Exception {
        String str = "2016-06-14T09:12:23.333Z";

        String suffix = str.substring(str.length()-4,str.length()-1);

        String strDate = str.substring(0,str.length() - 1);
        System.out.println(str);
        DateTimeZone tz = DateTimeZone.UTC;
        LocalDateTime date =  LocalDateTime.parse(strDate);

        //String fullDateStr = date.toDateTime(tz).toDateTime(DateTimeZone.getDefault()).toString("yyyy-MM-dd'T'HH:mm:ss") + "." + suffix;

        //String fixDate = date.toDateTime(tz).toDateTime(DateTimeZone.getDefault()).toString("yyyy-MM-dd HH:mm:ss") + "." + suffix;

        long time = date.toDateTime(tz).toDateTime(DateTimeZone.getDefault()).toDate().getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date(time)));
        System.out.println(date.toDateTime(tz).toDateTime(DateTimeZone.getDefault()).toString("yyyy-MM-dd HH:mm:ss.sss"));
        //System.out.println(fullDateStr);
        //System.out.println(LocalDateTime.parse(fullDateStr));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("@timestamp").gt(new Date(time)));
        System.out.println("");
    }
}
