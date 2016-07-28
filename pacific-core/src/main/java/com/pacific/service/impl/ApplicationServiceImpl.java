package com.pacific.service.impl;

import com.pacific.common.exception.PacificException;
import com.pacific.common.json.FastJson;
import com.pacific.domain.dto.ApplicationDto;
import com.pacific.domain.dto.ChannelDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.ApplicationUserConfig;
import com.pacific.domain.entity.User;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.ApplicationQuery;
import com.pacific.domain.query.Pagination;
import com.pacific.domain.query.UserQuery;
import com.pacific.mapper.ApplicationMapper;
import com.pacific.mapper.UserMapper;
import com.pacific.service.ApplicationService;
import com.pacific.service.ApplicationUserConfigService;
import com.pacific.service.ElasticSearchHelper;
import com.pacific.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fe on 16/5/30.
 */
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private ElasticSearchHelper elasticSearchHelper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApplicationUserConfigService applicationUserConfigService;

    public List<Application> queryApplicationByState(String state) {
        return applicationMapper.queryAllApplicationByState(state);
    }



    public Pagination<ApplicationDto> queryAllApplicationByPage(ApplicationQuery applicationQuery) {
        List<ApplicationDto> applicationList = applicationMapper.queryAllApplicationByParam(applicationQuery);
        Integer total = applicationMapper.queryTotalApplication(applicationQuery);
        Pagination<ApplicationDto> pagination = new Pagination<ApplicationDto>(applicationQuery, applicationList, total);
        return pagination;
    }

    public void saveApplication(Application application) {
        Assert.notNull(application);

        if (application.getId() == null) {
            Application app = applicationMapper.selectByApplicationCode(application.getApplicationCode());
            if (app != null) PacificException.throwEx("app has exists!");

            application.setCreateTime(new Date());
            application.setUpdateTime(new Date());
            application.setState(StateEnums.AVAILABLE.getCode());
            applicationMapper.insert(application);
            //elasticSearchHelper.createIndexResponse(application.getApplicationCode());

            List<User> userList = userMapper.queryAllUserList();
            List<ApplicationUserConfig> applicationUserConfigList = new ArrayList<ApplicationUserConfig>();
            for (User user : userList) {
                ApplicationUserConfig applicationUserConfig = new ApplicationUserConfig();
                applicationUserConfig.setUserId(user.getId());
                applicationUserConfig.setChannelConfig(FastJson.toJson(ChannelDto.getDefaultChannelList()));
                applicationUserConfig.setIsMonitorAllErrorLog("y");
                applicationUserConfig.setApplicationCode(application.getApplicationCode());
                applicationUserConfig.setUpdateTime(new Date());
                applicationUserConfig.setCreateTime(new Date());
                applicationUserConfig.setState(StateEnums.AVAILABLE.getCode());

                applicationUserConfigList.add(applicationUserConfig);
            }
            applicationUserConfigService.saveApplicationUserConfig(applicationUserConfigList);
        } else {
            Application app = applicationMapper.selectByPrimaryKey(application.getId());
            if (!app.getApplicationCode().equals(application.getApplicationCode()))  PacificException.throwEx("应用编码不能修改!");

            application.setUpdateTime(new Date());
            applicationMapper.updateByPrimaryKeySelective(application);
        }
    }
}
