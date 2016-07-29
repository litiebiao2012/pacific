package com.pacific.domain.dto.report;

import java.util.List;
import java.util.Map;

/**
 * Created by fe on 16/7/29.
 */
public class SevenDayWebUrlSeries {

    private String name;
    private String type;
    private String stack;
    private Map<String,Object> areaStyle;
    private List<Integer> data;

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

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
