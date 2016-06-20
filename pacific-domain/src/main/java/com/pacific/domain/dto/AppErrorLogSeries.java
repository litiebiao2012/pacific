package com.pacific.domain.dto;

import java.util.List;
import java.util.Map;

public class AppErrorLogSeries {
    private String name;
    private String type;
    private String stack;
    private Map<String,Object> areaStyle;
    private List<Long> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Map<String, Object> getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(Map<String, Object> areaStyle) {
        this.areaStyle = areaStyle;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
