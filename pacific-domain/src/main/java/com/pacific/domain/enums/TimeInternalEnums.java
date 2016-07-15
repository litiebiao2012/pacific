package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/7/14.
 */
public enum TimeInternalEnums {
    TEN_MINUTES("tenMinutes","可用"),
    THIRTY_MINUTES("thirtyMinutes","可用"),
    ONE_HOUR("oneHour","可用"),
    THREE_HOUR("threeHour","可用"),
    ONE_DAY("oneDay","不可用");

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
