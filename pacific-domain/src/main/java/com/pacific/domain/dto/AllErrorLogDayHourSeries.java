package com.pacific.domain.dto;

import java.util.List;

/**
 * Created by Fe on 16/6/27.
 */
public class AllErrorLogDayHourSeries {
    private String name;
    private String type;
    private String stack;
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

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
