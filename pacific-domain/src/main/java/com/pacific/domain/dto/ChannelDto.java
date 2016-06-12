package com.pacific.domain.dto;

/**
 * Created by Fe on 16/6/7.
 */
public class ChannelDto {
    /** 渠道code **/
    private String channelCode;
    /** 渠道名称 **/
    private String channelName;
    /** 是否开启 **/
    private boolean isOpen;
    /** 通知次数 **/
    private int notifyCount;
    /** 延时时间间隔(每隔多长时间通知一次) 单位s , 300s ,  **/
    private int delay;

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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
