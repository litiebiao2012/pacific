package com.pacific.domain.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fe on 16/7/15.
 */
public class TimeRangeDto {
    public static final String DEFAULT_DATE_FORMAT = "MM-dd HH:mm:ss";

    private List<Date> timeList;

    private List<TimeRange> timeRangeDtoList;

    public List<Date> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Date> timeList) {
        this.timeList = timeList;
    }

    public List<TimeRange> getTimeRangeDtoList() {
        return timeRangeDtoList;
    }

    public void setTimeRangeDtoList(List<TimeRange> timeRangeDtoList) {
        this.timeRangeDtoList = timeRangeDtoList;
    }

    public  List<String> getFormatTimeList() {
        List<String> strList = new LinkedList<String>();
        if (timeList != null && timeList.size() > 0) {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            for (Date date : timeList) {
                strList.add(format.format(date));
            }
        }
        return strList;
    }
}


