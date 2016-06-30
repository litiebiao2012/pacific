package com.pacific.service;

import com.pacific.domain.dto.ApplicationDto;
import com.pacific.domain.entity.Application;
import com.pacific.domain.query.ApplicationQuery;
import com.pacific.domain.query.Pagination;

import java.util.List;

/**
 * Created by Fe on 16/5/30.
 */
public interface ApplicationService {

    public List<Application> queryApplicationByState(String state);


    public Pagination<ApplicationDto> queryAllApplicationByPage(ApplicationQuery applicationQuery);


    public void saveApplication(Application application);

}
