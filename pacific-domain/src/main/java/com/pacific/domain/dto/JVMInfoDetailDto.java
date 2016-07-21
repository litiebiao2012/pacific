package com.pacific.domain.dto;

/**
 * Created by fe on 16/7/21.
 */
public class JVMInfoDetailDto {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static JVMInfoDetailDto buildJVMInfoDetail(String name,String value) {
        JVMInfoDetailDto jvmInfoDetailDto = new JVMInfoDetailDto();
        jvmInfoDetailDto.setName(name);
        jvmInfoDetailDto.setValue(value);
        return jvmInfoDetailDto;
    }
}
