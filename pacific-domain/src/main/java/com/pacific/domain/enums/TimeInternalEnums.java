package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/7/14.
 */
public enum TimeInternalEnums {
    TEN_MINUTES("tenMinutes","10分钟"),
    THIRTY_MINUTES("thirtyMinutes","30分钟"),
    ONE_HOUR("oneHour","1小时"),
    THREE_HOUR("threeHour","3小时"),
    ONE_DAY("oneDay","1天");

    TimeInternalEnums(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getText() {
        return text;
    }
    public String getCode() {
        return code;
    }

    public static TimeInternalEnums fromCode(String code) {
        for (TimeInternalEnums timeInternalEnums : TimeInternalEnums.values()) {
            if (StringUtils.equals(code, timeInternalEnums.getCode())) {
                return timeInternalEnums;
            }
        }
        return null;
    }
}
