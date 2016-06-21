package com.pacific.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/6/20.
 *
 *
 *
 * option = {
 title : {
 text: '某站点用户访问来源',
 subtext: '纯属虚构',
 x:'center'
 },
 tooltip : {
 trigger: 'item',
 formatter: "{a} <br/>{b} : {c} ({d}%)"
 },
 legend: {
 orient: 'vertical',
 left: 'left',
 data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
 },
 series : [
 {
 name: '访问来源',
 type: 'pie',
 radius : '55%',
 center: ['50%', '60%'],
 data:[
 {value:335, name:'直接访问'},
 {value:310, name:'邮件营销'},
 {value:234, name:'联盟广告'},
 {value:135, name:'视频广告'},
 {value:1548, name:'搜索引擎'}
 ],
 itemStyle: {
 emphasis: {
 shadowBlur: 10,
 shadowOffsetX: 0,
 shadowColor: 'rgba(0, 0, 0, 0.5)'
 }
 }
 }
 ]
 };

 */
public class AllAppErrorLogReportDto {
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
