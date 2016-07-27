package com.pacific.domain.dto;

/**
 * Created by fe on 16/7/27.
 */
public class JdbcInfoDetailDto {
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


    public static JdbcInfoDetailDto buildJdbcInfoDetailDto(String name,String value) {
        JdbcInfoDetailDto jdbcInfoDetailDto = new JdbcInfoDetailDto();
        jdbcInfoDetailDto.setName(name);
        jdbcInfoDetailDto.setValue(value);
        return jdbcInfoDetailDto;
    }
}
