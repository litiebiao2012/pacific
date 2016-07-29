package com.pacific.domain.dto.report;

import com.pacific.domain.dto.AppErrorLogSeries;

import java.util.List;
import java.util.Map;

/**
 * Created by fe on 16/7/29.
 */
public class SevenDayWebUrlReport extends BaseReportDto {
    private Map<String,List<String>> legend;
    private Map<String,Object> toolbox;
    private List<Map<String,Object>> xAxis;
    private List<Map<String,Object>> yAxis;
    private List<SevenDayWebUrlSeries> series;

    public Map<String, List<String>> getLegend() {
        return legend;
    }

    public void setLegend(Map<String, List<String>> legend) {
        this.legend = legend;
    }

    public Map<String, Object> getToolbox() {
        return toolbox;
    }

    public void setToolbox(Map<String, Object> toolbox) {
        this.toolbox = toolbox;
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

    public List<SevenDayWebUrlSeries> getSeries() {
        return series;
    }

    public void setSeries(List<SevenDayWebUrlSeries> series) {
        this.series = series;
    }
}
