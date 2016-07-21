package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/5/30.
 */
public enum MonitorTypeEnums {

    JVM_REPORT("jvmReport","jvm内存详情监控"),
    JVM_INFO("jvmInfo","jvm信息"),
    SPRING_METHOD("springMethod","service函数执行监控"),
    WEB_URL("webUrl","web接口请求监控"),
    SQL("sql","sql语句监控"),
    DRUID_DATASOURCE("druidDatasource","druid数据源监控");

    MonitorTypeEnums(String code, String text) {
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

    public static MonitorTypeEnums fromCode(String code) {
        for (MonitorTypeEnums monitorTypeEnums : MonitorTypeEnums.values()) {
            if (StringUtils.equals(code, monitorTypeEnums.getCode())) {
                return monitorTypeEnums;
            }
        }
        return null;
    }
}
