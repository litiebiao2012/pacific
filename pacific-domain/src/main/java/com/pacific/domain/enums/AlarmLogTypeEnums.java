package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by fe on 16/7/28.
 */
public enum AlarmLogTypeEnums {

    SPRING_METHOD("springMethod","函数方法"),
    WEB_URL("webUrl","url请求"),
    SQL("sql","sql语句");

    AlarmLogTypeEnums(String code, String text) {
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

    public static AlarmLogTypeEnums fromCode(String code) {
        for (AlarmLogTypeEnums alarmLogTypeEnums : AlarmLogTypeEnums.values()) {
            if (StringUtils.equals(code, alarmLogTypeEnums.getCode())) {
                return alarmLogTypeEnums;
            }
        }
        return null;
    }
}
