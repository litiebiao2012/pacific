package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/5/30.
 */
public enum ErrorLogTypeEnums {

    JAVA("java","java日志"),
    APACHE("apache","apache日志"),
    NGINX("nginx","nginx日志");

    ErrorLogTypeEnums(String code, String text) {
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

    public static ErrorLogTypeEnums fromCode(String code) {
        for (ErrorLogTypeEnums errorLogTypeEnum : ErrorLogTypeEnums.values()) {
            if (StringUtils.equals(code, errorLogTypeEnum.getCode())) {
                return errorLogTypeEnum;
            }
        }
        return null;
    }
}
