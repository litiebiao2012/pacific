package com.pacific.service;

import com.pacific.common.exception.PacificException;
import com.pacific.common.utils.DateUtil;
import com.pacific.domain.dto.TimeRange;
import com.pacific.domain.dto.TimeRangeDto;
import com.pacific.domain.enums.TimeInternalEnums;

import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/7/14.
 */
public class TimeInternalHelper {

    public static final int DEFAULT_SPLIT_COUNT = 4;

    public static final String DEFAULT_DATE_FORMAT = "MM-dd HH:mm:ss";

    public static TimeRangeDto getTimeRangeByInternal(String timeInternal) {
        TimeInternalEnums timeInternalEnums = TimeInternalEnums.fromCode(timeInternal);
        if (timeInternal == null) PacificException.throwEx("error timeInternal");

        String code = timeInternalEnums.getCode();
        if (code.equals(TimeInternalEnums.TEN_MINUTES.getCode())) {
            Date date = new Date();

            TimeRangeDto timeRangeDto = new TimeRangeDto();
            for (int i = 1; i < DEFAULT_SPLIT_COUNT; i++) {
                Date beforeMinutesDate = DateUtil.getBeforeMinutesDate(date,2);
                date = beforeMinutesDate;

            }


        }

        if (code.equals(TimeInternalEnums.THIRTY_MINUTES.getCode())) {

        }

        if (code.equals(TimeInternalEnums.ONE_HOUR.getCode())) {

        }

        if (code.equals(TimeInternalEnums.THREE_HOUR.getCode())) {

        }

        if (code.equals(TimeInternalEnums.ONE_DAY.getCode())) {

        }
        return null;

    }
}
