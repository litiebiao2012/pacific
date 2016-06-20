package com.pacific.service;

import com.pacific.domain.entity.Application;

import java.util.List;

/**
 * Created by Fe on 16/5/30.
 */
public interface ApplicationService {

    public List<Application> queryAllApplication();

    public Integer getTotalApplication();

}
