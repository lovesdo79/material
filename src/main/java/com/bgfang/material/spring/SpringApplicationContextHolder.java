package com.bgfang.material.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @description Spring应用上下文环境
 * @author xdwang
 * @create 2015年11月22日上午10:25:09
 * @version 1.0
 */
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
