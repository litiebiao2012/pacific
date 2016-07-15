package com.pacific.domain.dto;

import java.util.List;

/**
 * Created by Fe on 16/7/15.
 */
public class TimeRangeDto {
    private List<String> timeList;

    private List<TimeRange> timeRangeDtoList;

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public List<TimeRange> getTimeRangeDtoList() {
        return timeRangeDtoList;
    }

    public void setTimeRangeDtoList(List<TimeRange> timeRangeDtoList) {
        this.timeRangeDtoList = timeRangeDtoList;
    }
}


