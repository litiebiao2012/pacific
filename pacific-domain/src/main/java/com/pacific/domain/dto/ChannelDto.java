package com.pacific.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fe on 16/6/7.
 */
public class ChannelDto {
    /** 渠道code **/
    private String channelCode;
    /** 渠道名称 **/
    private String channelName;
    /** 是否开启 **/
    private String isOpen;
//    /** 通知次数 **/
//    private int notifyCount;
//    /** 延时时间间隔(每隔多长时间通知一次) 单位s , 300s ,  **/
//    private int delay;

    public static List<ChannelDto> channelDtoList = new ArrayList<ChannelDto>();

    static {
        ChannelDto channelDto = new ChannelDto();
        channelDto.setChannelCode("bearyChat");
        channelDto.setChannelName("bearyChat报警");
        channelDto.setIsOpen("y");
        channelDtoList.add(channelDto);
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public static List<ChannelDto> getDefaultChannelList() {
        return channelDtoList;
    }
}
