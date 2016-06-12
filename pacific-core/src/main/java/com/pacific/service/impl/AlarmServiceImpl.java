package com.pacific.service.impl;

import com.pacific.common.json.FastJson;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.NamedThreadFactory;
import com.pacific.domain.dto.ChannelDto;
import com.pacific.domain.entity.ApplicationUserConfig;
import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.mapper.ApplicationUserConfigMapper;
import com.pacific.mapper.ErrorLogRecordMapper;
import com.pacific.service.AlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fe on 16/5/27.
 */
public class AlarmServiceImpl implements AlarmService {

    public static final Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1,new NamedThreadFactory("alarm-error-log"));

    @Resource
    private ErrorLogRecordMapper errorLogRecordMapper;

    @Resource
    private ApplicationUserConfigMapper applicationUserConfigMapper;


    @PostConstruct
    public void init() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                alarm();
            }
        },0,2, TimeUnit.SECONDS);
    }


    @Override
    public void alarm() {
        List<ErrorLogRecord> errorLogRecordList = errorLogRecordMapper.queryHasNoAlarmErrorLogRecord();
        logger.info("will be alarm error log data : {}", FastJson.toJson(errorLogRecordList));
        if (CollectionUtil.isNotEmpty(errorLogRecordList)) {

            Map<String,List<ApplicationUserConfig>> appMap = groupByApplicationCode();

            if (appMap != null && appMap.size() > 0) {
                for (ErrorLogRecord errorLogRecord : errorLogRecordList) {
                    String applicationCode = errorLogRecord.getApplicationCode();

                    List<ApplicationUserConfig> applicationUserConfigList = appMap.get(applicationCode);
                    if (CollectionUtil.isNotEmpty(applicationUserConfigList)) {
                        //TODO 根据每个用户的报警规则 开启报警
                        for (ApplicationUserConfig applicationUserConfig : applicationUserConfigList) {
                            String isMonitorAllErrorLog = applicationUserConfig.getIsMonitorAllErrorLog();
                            String channelConfig = applicationUserConfig.getChannelConfig();
                            if (isMonitorAllErrorLog.equals("y")) {

                            } else {
                                String logMessage = errorLogRecord.getLogMessage();
                            }
                        }
                    }
                }
            }
        }
    }

    private List<ChannelDto> buildChannelList(String channelConfigJson) {
        return FastJson.jsonToList(channelConfigJson,ChannelDto.class);
    }

    private Map<String,List<ApplicationUserConfig>> groupByApplicationCode() {
        List<ApplicationUserConfig> applicationUserConfigList = applicationUserConfigMapper.queryAllApplicationUserConfigByCode();
        Map<String,List<ApplicationUserConfig>> userConfigMap = new HashMap<String,List<ApplicationUserConfig>>();

        if (CollectionUtil.isNotEmpty(applicationUserConfigList)) {
            for (ApplicationUserConfig applicationUserConfig : applicationUserConfigList) {
                String applicationCode = applicationUserConfig.getApplicationCode();

                List<ApplicationUserConfig> appList = null;
                if (userConfigMap.containsKey(applicationCode)) {
                    appList = userConfigMap.get(applicationCode);
                } else {
                    appList = new ArrayList<ApplicationUserConfig>();
                    userConfigMap.put(applicationCode,appList);
                }
                appList.add(applicationUserConfig);
            }
        }
        return userConfigMap;
    }
}
