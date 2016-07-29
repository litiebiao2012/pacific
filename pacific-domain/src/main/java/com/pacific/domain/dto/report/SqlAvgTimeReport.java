package com.pacific.domain.dto.report;

import java.util.List;
import java.util.Map;

/**
 * Created by fe on 16/7/29.
 */
public class SqlAvgTimeReport {
    private Map<String,String> title;
    private Map<String,String> tooltip;
    private Map<String,Object> legend;
    private List<Map<String,Object>> series;


    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public Map<String, String> getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map<String, String> tooltip) {
        this.tooltip = tooltip;
    }

    public Map<String, Object> getLegend() {
        return legend;
    }

    public void setLegend(Map<String, Object> legend) {
        this.legend = legend;
    }

    public List<Map<String, Object>> getSeries() {
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }
}
