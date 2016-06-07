package com.pacific.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fe on 16/5/30.
 */
public enum ChannelCodeEnums {

    DING_TALK("java","钉钉报警"),
    PHONE_MESSAGE("phoneMessage","短信报警"),
    EMAIL("email","邮件报警"),
    WE_CHAT("weChat","微信报警"),
    BEARY_CHAT("bearyChat","bearyChat平台报警");

    ChannelCodeEnums(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getText() {
        return text;
    }
    public String getCode() {
        return code;
    }

    public static ChannelCodeEnums fromCode(String code) {
        for (ChannelCodeEnums channelCodeEnums : ChannelCodeEnums.values()) {
            if (StringUtils.equals(code, channelCodeEnums.getCode())) {
                return channelCodeEnums;
            }
        }
        return null;
    }
}
