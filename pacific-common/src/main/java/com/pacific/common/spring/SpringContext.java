package com.pacific.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Fe on 16/2/27.
 */
public class SpringContext implements ApplicationContextAware {

    private static Logger logger             = LoggerFactory.getLogger(SpringContext.class);

    protected static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }

    public static <T> T getBean(Class<T> requiredType) {
        try {
            return (T) applicationContext.getBean(requiredType);
        } catch (Exception e) {
            logger.error("get bean error: " + e.getMessage());
        }
        return null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void clearHolder() {
        applicationContext = null;
    }

    public void destroy() throws Exception {
        SpringContext.clearHolder();
    }

}
