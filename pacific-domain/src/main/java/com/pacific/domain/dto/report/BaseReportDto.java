package com.pacific.domain.dto.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fe on 16/7/14.
 */
public class BaseReportDto {
    protected Map<String,Object> grid = new HashMap<String,Object>(){
        {
            put("left","3%");
            put("right","4%");
            put("bottom","3%");
            put("containLabel",true);
        }
    };

    protected Map<String,Object> tooltip = new HashMap<String,Object>() {
        {
            put("trigger","axis");
        }
    };

    public Map<String, Object> getGrid() {
        return grid;
    }

    public void setGrid(Map<String, Object> grid) {
        this.grid = grid;
    }

    public Map<String, Object> getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map<String, Object> tooltip) {
        this.tooltip = tooltip;
    }
}
