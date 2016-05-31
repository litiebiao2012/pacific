package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/5/30.
 */
public enum StateEnums {

    AVAILABLE("available","可用"),
    DISABLES("disables","不可用");

    StateEnums(String code, String text) {
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

    public static StateEnums fromCode(String code) {
        for (StateEnums stateEnum : StateEnums.values()) {
            if (StringUtils.equals(code, stateEnum.getCode())) {
                return stateEnum;
            }
        }
        return null;
    }
}
