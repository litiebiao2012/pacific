package com.pacific.domain.dto.jvm;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Fe on 16/7/15.
 */
public class WebUrlDto extends BaseDto {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
