package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/7/5.
 */
public enum RoleTypeEnums {
    ADMIN("admin","管理员"),
    PERSONAL("personal","普通用户");

    RoleTypeEnums(String code, String text) {
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

    public static RoleTypeEnums fromCode(String code) {
        for (RoleTypeEnums roleTypeEnum : RoleTypeEnums.values()) {
            if (StringUtils.equals(code, roleTypeEnum.getCode())) {
                return roleTypeEnum;
            }
        }
        return null;
    }
}
