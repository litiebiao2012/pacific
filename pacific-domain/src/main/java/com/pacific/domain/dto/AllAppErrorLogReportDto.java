package com.pacific.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/6/20.
 *
 * option = {
 title: {
 text: '堆叠区域图'
 },
 tooltip : {
 trigger: 'axis'
 },
 legend: {
 data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
 },
 toolbox: {
 feature: {
 saveAsImage: {}
 }
 },
 grid: {
 left: '3%',
 right: '4%',
 bottom: '3%',
 containLabel: true
 },
 xAxis : [
 {
 type : 'category',
 boundaryGap : false,
 data : ['周一','周二','周三','周四','周五','周六','周日']
 }
 ],
 yAxis : [
 {
 type : 'value'
 }
 ],
 series : [
 {
 name:'邮件营销',
 type:'line',
 stack: '总量',
 areaStyle: {normal: {}},
 data:[120, 132, 101, 134, 90, 230, 210]
 },
 {
 name:'联盟广告',
 type:'line',
 stack: '总量',
 areaStyle: {normal: {}},
 data:[220, 182, 191, 234, 290, 330, 310]
 },
 {
 name:'视频广告',
 type:'line',
 stack: '总量',
 areaStyle: {normal: {}},
 data:[150, 232, 201, 154, 190, 330, 410]
 },
 {
 name:'直接访问',
 type:'line',
 stack: '总量',
 areaStyle: {normal: {}},
 data:[320, 332, 301, 334, 390, 330, 320]
 },
 {
 name:'搜索引擎',
 type:'line',
 stack: '总量',
 label: {
 normal: {
 show: true,
 position: 'top'
 }
 },
 areaStyle: {normal: {}},
 data:[820, 932, 901, 934, 1290, 1330, 1320]
 }
 ]
 };

 *
 */
public class AllAppErrorLogReportDto {

    private Map<String,String> title;
    private Map<String,String> tooltip;
    private Map<String,List<String>> legend;
    private Map<String,Object> toolbox;
    private Map<String,Object> grid;
    private List<Map<String,Object>> xAxis;
    private List<Map<String,Object>> yAxis;
    private List<AppErrorLogSeries> series;

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

    public List<AppErrorLogSeries> getSeries() {
        return series;
    }

    public void setSeries(List<AppErrorLogSeries> series) {
        this.series = series;
    }
}
