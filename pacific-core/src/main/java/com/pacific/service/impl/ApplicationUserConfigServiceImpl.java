package com.pacific.service.impl;

import com.pacific.domain.dto.ChannelDto;
import com.pacific.domain.entity.ApplicationUserConfig;
import com.pacific.mapper.ApplicationUserConfigMapper;
import com.pacific.service.ApplicationUserConfigService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fe on 16/7/5.
 */
public class ApplicationUserConfigServiceImpl  implements ApplicationUserConfigService {

    public static List<ChannelDto> channelDtoList = new ArrayList<ChannelDto>();

    static {
        ChannelDto channelDto = new ChannelDto();
        channelDto.setChannelCode("bearyChat");
        channelDto.setChannelName("bearyChat报警");
        channelDto.setIsOpen("y");
        channelDtoList.add(channelDto);
    }

    @Resource
    private ApplicationUserConfigMapper applicationUserConfigMapper;

    public void saveApplicationUserConfig(List<ApplicationUserConfig> applicationUserConfigList) {
    }
}
