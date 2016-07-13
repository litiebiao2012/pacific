package com.pacific.mapper;

import com.pacific.domain.dto.ApplicationUserConfigDto;
import com.pacific.domain.entity.ApplicationUserConfig;

import java.util.List;

public interface ApplicationUserConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApplicationUserConfig record);

    int insertSelective(ApplicationUserConfig record);

    ApplicationUserConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplicationUserConfig record);

    int updateByPrimaryKey(ApplicationUserConfig record);

    List<ApplicationUserConfig> queryApplicationUserConfigByCode(String applicationCode);

    List<ApplicationUserConfigDto> queryAllApplicationUserConfigByCode();

    void batchSaveApplicationUserConfig(List<ApplicationUserConfig> applicationUserConfigList);
}