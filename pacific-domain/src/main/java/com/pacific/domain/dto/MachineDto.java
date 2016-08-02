package com.pacific.domain.dto;

import com.pacific.domain.entity.Machine;

/**
 * Created by fe on 16/8/2.
 */
public class MachineDto extends Machine {
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
