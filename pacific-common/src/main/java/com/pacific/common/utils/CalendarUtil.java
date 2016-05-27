package com.pacific.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanghua on 2015/8/19.
 */
public class CalendarUtil {

    private static final String common_pattern = "yyyy-MM-dd HH:mm:ss";
    private static final String ymd_pattern    = "yyyy-MM-dd";

    /**
     * 是否在两个日期之间，包含起始和结束时间,如果before或者end为null则代表是无限
     * @param before
     * @param end
     * @return
     */
    public static boolean isBetweenDate(Object middle, Object before, Object end) {
        return isInRange(middle, before, end, true);
    }

    /**
     * 是否在两个日期之间，不包含起始和结束时间,如果before或者end为null则代表是无限
     * @param before
     * @param end
     * @return
     */
    public static boolean isInDate(Object middle, Object before, Object end) {
        return isInRange(middle, before, end, false);
    }

    /**
     * 是否在两个日期之间，不包含起始和结束时间,如果before或者end为null则代表是无限
     * @param middle
     * @param before
     * @param end
     * @param hasBoundary  是否包含起终边界,true包含，false不包含
     * @return
     */
    private static boolean isInRange(Object middle, Object before, Object end, boolean hasBoundary) {
        Date middleDate = convertDate(middle);
        Date beforeDate = convertDate(before);
        Date afterDate = convertDate(end);

        if (middleDate == null) {
            return false;
        }
        if (beforeDate == null && afterDate != null) {
            int value = middleDate.compareTo(afterDate);
            if (hasBoundary && value == 0) {
                return true;
            }
            if (value < 0) {
                return true;
            } else {
                return false;
            }
        }
        if (beforeDate != null && afterDate == null) {
            int value = middleDate.compareTo(beforeDate);
            if (hasBoundary && value == 0) {
                return true;
            }
            if (value > 0) {
                return true;
            } else {
                return false;
            }
        }
        if (before == null && end == null) {
            return true;
        }
        //包含边界
        if (hasBoundary) {
            if (middleDate.compareTo(beforeDate) >= 0 && middleDate.compareTo(afterDate) <= 0) return true;
        } else {
            //不包含边界
            if (middleDate.compareTo(beforeDate) > 0 && middleDate.compareTo(afterDate) < 0) return true;
        }

        return false;
    }

    private static Date convertDate(Object obj) {
        if (obj != null) {
            if (obj instanceof Calendar) {
                Calendar cal = (Calendar) obj;
                return cal.getTime();
            }
            if (obj instanceof Date) {
                return (Date) obj;
            }
        }
        return null;
    }

    /**
     * 按照常规默式去格式化日期
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(common_pattern);
        return format.format(date);

    }
    /**
     * 按照常规默式去格式化日期
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(common_pattern);
        try{
            return format.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 返回年月日的格式
     * @param date
     * @return
     */
    public static String formatYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(ymd_pattern);
        return format.format(date);
    }

    /**
     * 按年月日的格式解析日期
     * @param dateStr
     * @return
     */
    public static Date parseYMD(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(ymd_pattern);
        try {
            return format.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}
