package com.pacific.service.impl;

import com.pacific.common.Constants;
import com.pacific.common.exception.PacificException;
import com.pacific.common.helper.BearyChatHelper;
import com.pacific.common.helper.MailHelper;
import com.pacific.common.helper.PhoneHelper;
import com.pacific.common.json.FastJson;
import com.pacific.common.utils.CollectionUtil;
import com.pacific.common.utils.NamedThreadFactory;
import com.pacific.common.utils.VelocityTemplateUtil;
import com.pacific.domain.dto.ApplicationUserConfigDto;
import com.pacific.domain.dto.ChannelDto;
import com.pacific.domain.dto.SpringMethodErrorDto;
import com.pacific.domain.dto.jvm.SpringMethodDto;
import com.pacific.domain.entity.AlarmLog;
import com.pacific.domain.entity.AlarmTemplate;
import com.pacific.domain.entity.ErrorLogRecord;
import com.pacific.domain.entity.SpringMethod;
import com.pacific.domain.enums.AlarmLogTypeEnums;
import com.pacific.domain.enums.ChannelCodeEnums;
import com.pacific.mapper.*;
import com.pacific.service.AlarmService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Fe on 16/5/27.
 */
public class AlarmServiceImpl implements AlarmService {

    public static final Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1,new NamedThreadFactory("alarm-error-log"));

    @Resource
    private ApplicationUserConfigMapper applicationUserConfigMapper;

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Resource
    private SpringMethodMapper springMethodMapper;

    @Resource
    private AlarmTemplateMapper alarmTemplateMapper;

    @Resource
    private BearyChatHelper bearyChatHelper;

    @Resource
    private PhoneHelper phoneHelper;

    @Resource
    private MailHelper mailHelper;


    @PostConstruct
    public void init() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                alarm();
            }
        },0,Constants.ALARM_ERROR_LOG_SCHEDULE_DELAY, TimeUnit.SECONDS);
    }


    @Override
    public void alarm() {
        try {
            List<SpringMethodErrorDto> errorLogRecordList = springMethodMapper.queryHasNoAlarmErrorSpringMethod();
            logger.info("will be alarm error log data : {}", FastJson.toJson(errorLogRecordList));
            if (CollectionUtil.isNotEmpty(errorLogRecordList)) {
                Map<String,List<ApplicationUserConfigDto>> appMap = groupByApplicationCode();

                if (appMap != null && appMap.size() > 0) {
                    for (SpringMethodErrorDto errorSpringMethod : errorLogRecordList) {
                        String applicationCode = errorSpringMethod.getApplicationCode();

                        List<ApplicationUserConfigDto> applicationUserConfigDtoList = appMap.get(applicationCode);
                        if (CollectionUtil.isNotEmpty(applicationUserConfigDtoList)) {
                            //TODO 根据每个用户的报警规则 开启报警
                            for (ApplicationUserConfigDto applicationUserConfigDto : applicationUserConfigDtoList) {
                                processApplicationUserConfig(applicationUserConfigDto,errorSpringMethod);
                            }
                        }

                        //TODO 更新错误日志为已处理
                        SpringMethod updateSpringMethod = new SpringMethod();
                        updateSpringMethod.setId(errorSpringMethod.getId());
                        updateSpringMethod.setIsAlarm("y");
                        updateSpringMethod.setUpdateTime(new Date());
                        springMethodMapper.updateByPrimaryKeySelective(updateSpringMethod);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("alarm error ,e {} ", e);
        }
    }

    private void processApplicationUserConfig(ApplicationUserConfigDto applicationUserConfigDto,SpringMethodErrorDto springMethodErrorDto) {
        String isMonitorAllErrorLog = applicationUserConfigDto.getIsMonitorAllErrorLog();
        String channelConfig = applicationUserConfigDto.getChannelConfig();
        String keyWords = applicationUserConfigDto.getMonitorErrorLogKeywords();
        List<ChannelDto> channelDtoList = buildChannelList(channelConfig);
        if (channelConfig == null || channelDtoList.size() == 0) PacificException.throwEx("applicationUserConfig appCode : {" + applicationUserConfigDto.getApplicationCode() + "} , userId : {" + applicationUserConfigDto.getUserId() + "}, has no config channelConfig,");

        for (ChannelDto channelDto : channelDtoList) {
            if (isMonitorAllErrorLog.equals("y")) {
                saveAlarmLog(channelDto,applicationUserConfigDto,springMethodErrorDto);
            } else {
                String logMessage = springMethodErrorDto.getErrorMsg();
                List<String> keyWordsList = FastJson.jsonToList(keyWords,String.class);
                boolean flag = checkKeyWordsIsExists(keyWordsList,logMessage);
                if (flag) {
                    saveAlarmLog(channelDto,applicationUserConfigDto,springMethodErrorDto);
                }
            }
        }
    }

    private void saveAlarmLog(ChannelDto channelDto,ApplicationUserConfigDto applicationUserConfigDto,
                              SpringMethodErrorDto errorSpringMethod) {
        AlarmTemplate alarmTemplate = alarmTemplateMapper.selectByChannelCode(channelDto.getChannelCode());

        if (alarmTemplate == null) PacificException.throwEx("alarmTemplate has not init!");
        String message = getMessage(alarmTemplate,applicationUserConfigDto,errorSpringMethod);

        if(channelDto.getChannelCode().equals(ChannelCodeEnums.BEARY_CHAT.getCode())) {
            //TODO bearychat由于是广播通知,所以只做一次报警
            int total = alarmLogMapper.queryTotalByParam(errorSpringMethod.getId(), AlarmLogTypeEnums.SPRING_METHOD.getCode(),channelDto.getChannelCode());
            if (total == 0) {
                alarmToAppUser(channelDto,message,applicationUserConfigDto);
            }
        } else {
            alarmToAppUser(channelDto,message,applicationUserConfigDto);
        }

        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setCreateTime(new Date());
        alarmLog.setUserId(applicationUserConfigDto.getUserId());
        alarmLog.setApplicationCode(applicationUserConfigDto.getApplicationCode());
        alarmLog.setChannelCode(channelDto.getChannelCode());

        alarmLog.setMessage(message);
        alarmLog.setSendTime(new Date());
        alarmLog.setUpdateTime(new Date());
        alarmLog.setObjectId(errorSpringMethod.getId());
        alarmLog.setType(AlarmLogTypeEnums.SPRING_METHOD.getCode());
        alarmLogMapper.insertSelective(alarmLog);


    }
    private String getMessage(AlarmTemplate alarmTemplate,ApplicationUserConfigDto applicationUserConfigDto,SpringMethodErrorDto springMethodErrorDto) {
        String templateText = alarmTemplate.getTemplateText();
        Context context = new VelocityContext();
        context.put("applicationName",applicationUserConfigDto.getApplicationName());
        context.put("errorMsg",springMethodErrorDto.getErrorMsg());
        context.put("logCreateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(springMethodErrorDto.getErrorTime()));
        context.put("logStackTrace",springMethodErrorDto.getErrorStackTrace());
        context.put("className",springMethodErrorDto.getClassName());
        context.put("method",springMethodErrorDto.getMethod());
        context.put("errorCount",springMethodErrorDto.getErrorCount());
        context.put("clientIp",springMethodErrorDto.getClientIp());
        context.put("hostName",springMethodErrorDto.getHostName());
        return VelocityTemplateUtil.merge(templateText,context);
    }



    private boolean checkKeyWordsIsExists(List<String> keyWordsList,String logMessage) {
        boolean flag = false;
        if (CollectionUtil.isNotEmpty(keyWordsList))  {
            for (String str : keyWordsList) {
                if (logMessage.contains(str))  {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private void alarmToAppUser(ChannelDto channelDto,String message,ApplicationUserConfigDto applicationUserConfigDto) {
        if (channelDto.getIsOpen().equals("y")) {
            if (channelDto.getChannelCode().equals(ChannelCodeEnums.PHONE_MESSAGE.getCode())) {
                phoneHelper.sendMessage(applicationUserConfigDto.getPhone(),message);
            }

            if (channelDto.getChannelCode().equals(ChannelCodeEnums.EMAIL.getCode())) {
                mailHelper.sendMail(applicationUserConfigDto.getEmail(),message);
            }
            if (channelDto.getChannelCode().equals(ChannelCodeEnums.BEARY_CHAT.getCode())) {
                bearyChatHelper.sendMessage(applicationUserConfigDto.getEmail(),applicationUserConfigDto.getApplicationName(),message);
            }
        }
    }

    private List<ChannelDto> buildChannelList(String channelConfigJson) {
        return FastJson.jsonToList(channelConfigJson,ChannelDto.class);
    }

    private Map<String,List<ApplicationUserConfigDto>> groupByApplicationCode() {
        List<ApplicationUserConfigDto> applicationUserConfigList = applicationUserConfigMapper.queryAllApplicationUserConfigByCode();
        Map<String,List<ApplicationUserConfigDto>> userConfigMap = new HashMap<String,List<ApplicationUserConfigDto>>();

        if (CollectionUtil.isNotEmpty(applicationUserConfigList)) {
            for (ApplicationUserConfigDto applicationUserConfigDto : applicationUserConfigList) {
                String applicationCode = applicationUserConfigDto.getApplicationCode();

                List<ApplicationUserConfigDto> appList = null;
                if (userConfigMap.containsKey(applicationCode)) {
                    appList = userConfigMap.get(applicationCode);
                } else {
                    appList = new ArrayList<ApplicationUserConfigDto>();
                    userConfigMap.put(applicationCode,appList);
                }
                appList.add(applicationUserConfigDto);
            }
        }
        return userConfigMap;
    }
}
