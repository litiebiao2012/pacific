package com.pacific.service.impl;

import com.pacific.domain.entity.Application;
import com.pacific.mapper.ApplicationMapper;
import com.pacific.service.ApplicationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fe on 16/5/30.
 */
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    public List<Application> queryAllApplication() {
        return null;
    }
}
