package com.pacific.domain.dto;

import com.pacific.domain.entity.Application;
import com.pacific.domain.enums.StateEnums;

/**
 * Created by Fe on 16/6/30.
 */
public class ApplicationDto extends Application {
    private String stateText;

    public String getStateText() {
        return StateEnums.fromCode(getState()).getText();
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }
}
