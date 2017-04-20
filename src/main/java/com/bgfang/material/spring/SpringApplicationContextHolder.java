package com.bgfang.material.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xdwang
 * @version 1.0
 * @description Spring应用上下文环境
 * @create 2015年11月22日上午10:25:09
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getWebApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }
}
