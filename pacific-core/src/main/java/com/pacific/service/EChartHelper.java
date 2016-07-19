package com.pacific.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fe on 16/7/18.
 */
public class EChartHelper {

    public static Map<String,Object> buildXAxis(List<String> timeList) {
        Map<String,Object> xAlias = new HashMap<String,Object>();
        xAlias.put("type","category");
        xAlias.put("boundaryGap",false);
        xAlias.put("data",timeList);
        return  xAlias;
    }

    public static Map<String,Object> buildYAxis() {
        Map<String,Object> yAlias = new HashMap<String,Object>();
        yAlias.put("type","value");
        return  yAlias;
    }

    public static Map<String,Object> buildLegend(String... legends) {
        Map<String,Object> legendMap = new HashMap<String,Object>();
        List<String> list = new LinkedList<String>();
        for (String str : legends) {
            list.add(str);
        }
        legendMap.put("data",list);
        return  legendMap;
    }
}
