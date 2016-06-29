package com.pacific.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/6/27.
 */
public class AllAppErrorLogDayHourReportDto {
    private Map<String,Object> tooltip;
    private Map<String,List<String>> legend;
    private Map<String,Object> grid;
    private List<Map<String,Object>> xAxis;
    private List<Map<String,Object>> yAxis;
    private List<AllErrorLogDayHourSeries> series;

    public Map<String, Object> getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map<String, Object> tooltip) {
        this.tooltip = tooltip;
    }

    public Map<String, List<String>> getLegend() {
        return legend;
    }

    public void setLegend(Map<String, List<String>> legend) {
        this.legend = legend;
    }

    public Map<String, Object> getGrid() {
        return grid;
    }

    public void setGrid(Map<String, Object> grid) {
        this.grid = grid;
    }

    public List<Map<String, Object>> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Map<String, Object>> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Map<String, Object>> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<Map<String, Object>> yAxis) {
        this.yAxis = yAxis;
    }

    public List<AllErrorLogDayHourSeries> getSeries() {
        return series;
    }

    public void setSeries(List<AllErrorLogDayHourSeries> series) {
        this.series = series;
    }
}
