package com.pacific.service;

import com.pacific.common.exception.PacificException;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.enums.TimeInternalEnums;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Fe on 16/7/14.
 */
public class TimeInternalHelper {

    public static final int DEFAULT_SPLIT_COUNT = 4;

    public static final String DEFAULT_DATE_FORMAT = "MM-dd HH:mm:ss";

    public static TimeRangeDto getTimeRangeByInternal(String timeInternal) {
        TimeInternalEnums timeInternalEnums = TimeInternalEnums.fromCode(timeInternal);
        if (timeInternalEnums == null) PacificException.throwEx("error timeInternal");

        String code = timeInternalEnums.getCode();
        if (code.equals(TimeInternalEnums.TEN_MINUTES.getCode())) {
            return buildTimeRangeDto("minutes",2);
        }

        if (code.equals(TimeInternalEnums.THIRTY_MINUTES.getCode())) {
            return buildTimeRangeDto("minutes",6);

        }

        if (code.equals(TimeInternalEnums.ONE_HOUR.getCode())) {
            return buildTimeRangeDto("minutes",12);

        }

        if (code.equals(TimeInternalEnums.THREE_HOUR.getCode())) {
            return buildTimeRangeDto("minutes",36);

        }

        if (code.equals(TimeInternalEnums.ONE_DAY.getCode())) {
            return buildTimeRangeDto("hour",3,6);
        }
        throw new RuntimeException("build TimeRangeDto error!");

    }

    private static TimeRangeDto buildTimeRangeDto(String unit,int step) {
       return buildTimeRangeDto(unit,DEFAULT_SPLIT_COUNT,step);
    }

    private static TimeRangeDto buildTimeRangeDto(String unit,int spliceCount,int step) {
        TimeRangeDto timeRangeDto = new TimeRangeDto();
        List<Date> timeList = new LinkedList<Date>();
        Date date = new Date();
        timeList.add(date);
        for (int i = 1; i <= spliceCount; i++) {
            date = getBeforeDate(date,unit,"before",step);
            timeList.add(date);
        }
        sortDateList(timeList);
        List<TimeRange> timeRangeList = buildTimeRangeList(timeList,unit,step);
        timeRangeDto.setTimeList(timeList);
        timeRangeDto.setTimeRangeDtoList(timeRangeList);
        return timeRangeDto;
    }

    private static Date getBeforeDate(Date date,String unit,String type,int step) {
        Date returnDate = null;
        if (unit.equals("minutes")) {
            if (type.equals("before")) {
                returnDate = DateUtil.getBeforeMinutesDate(date,step);
            }
        }
        if (unit.equals("hour")) {
            if (type.equals("before")) {
                returnDate = DateUtil.getBeforeHourDate(date,step);
            }
        }
        return returnDate;
    }


    private static void sortDateList(List<Date> dateList) {
        if (CollectionUtil.isNotEmpty(dateList)) {
            Collections.sort(dateList, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    if (o1.getTime() > o2.getTime()) {
                        return 1;
                    } else if (o1.getTime() < o2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    private static List<TimeRange> buildTimeRangeList(List<Date> dateList,String unit,int step) {
        List<TimeRange> timeRangeList = new LinkedList<TimeRange>();
        if (CollectionUtil.isNotEmpty(dateList)) {
            int j = 1;
            for (int i = 0;i < dateList.size(); i ++) {
                TimeRange timeRange = new TimeRange();
                Date endDate = dateList.get(i);
                Date beginDate = getBeforeDate(endDate,unit,"before",step);

                timeRange.setBeginDate(beginDate);
                timeRange.setEndDate(endDate);
                timeRangeList.add(timeRange);
            }
        }
        return timeRangeList;
    }

    public static List<String> formatDateList(List<Date> dateList) {
        List<String> strList = new LinkedList<String>();
        if (CollectionUtil.isNotEmpty(dateList)) {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            for (Date date : dateList) {
                strList.add(format.format(date));
            }
        }
        return strList;
    }
}
