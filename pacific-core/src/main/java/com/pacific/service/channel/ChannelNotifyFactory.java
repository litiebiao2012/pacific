package com.pacific.service.channel;

import java.util.Map;

/**
 * Created by Fe on 16/6/7.
 */
public class ChannelNotifyFactory {

    private Map<String,ChannelNotifyInterface> channelNotifyInterfaceMap;

    public Map<String, ChannelNotifyInterface> getChannelNotifyInterfaceMap() {
        return channelNotifyInterfaceMap;
    }

    public void setChannelNotifyInterfaceMap(Map<String, ChannelNotifyInterface> channelNotifyInterfaceMap) {
        this.channelNotifyInterfaceMap = channelNotifyInterfaceMap;
    }

    public ChannelNotifyInterface getChannelNotifyInterface() {
        return null;
    }
}
