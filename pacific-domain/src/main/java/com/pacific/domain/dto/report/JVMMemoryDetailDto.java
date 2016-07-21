package com.pacific.domain.dto.report;


import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/7/20.
 */
public class JVMMemoryDetailDto extends BaseReportDto {
    private Map<String,Object> legend;
    private Map<String,Object> xAxis;
    private Map<String,Object> yAxis;
    private List<Map<String,Object>> series;

    public Map<String, Object> getLegend() {
        return legend;
    }

    public void setLegend(Map<String, Object> legend) {
        this.legend = legend;
    }

    public Map<String, Object> getxAxis() {
        return xAxis;
    }

    public void setxAxis(Map<String, Object> xAxis) {
        this.xAxis = xAxis;
    }

    public Map<String, Object> getyAxis() {
        return yAxis;
    }

    public void setyAxis(Map<String, Object> yAxis) {
        this.yAxis = yAxis;
    }

    public List<Map<String, Object>> getSeries() {
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }
}
