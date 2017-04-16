package com.bgfang.material.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @description Spring上下文工厂
 * @author xdwang
 * @create 2015年11月22日上午10:29:57
 * @version 1.0
 */
public class SpringContextBeanFactory {

	/**
	 * @description 获取对象bean
	 * @author xdwang
	 * @create 2015年11月22日上午10:30:17
	 * @version 1.0
	 * @param <T>
	 *            泛型
	 * @param name
	 *            类名
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		try {
			return (T) SpringApplicationContextHolder.getWebApplicationContext().getBean(name);
		} catch (Exception e) {
			return (T) SpringDispatcherContextHolder.getWebApplicationContext().getBean(name);
		}
	}

	/**
	 * @description 根据反射类获取对象
	 * @author xdwang
	 * @create 2015年11月22日上午10:29:10
	 * @version 1.0
	 * @param <T>
	 *            泛型
	 * @param clazz
	 *            类class
	 * @return 对象实体
	 */
	public static <T> T getBean(Class<T> clazz) {
		try {
			return SpringApplicationContextHolder.getWebApplicationContext().getBean(clazz);
		} catch (Exception e) {
			return SpringDispatcherContextHolder.getWebApplicationContext().getBean(clazz);
		}

	}

	/**
	 * @description 根据名称或反射类获取对象
	 * @author xdwang
	 * @create 2015年11月22日上午10:29:10
	 * @version 1.0
	 * @param <T>
	 *            泛型
	 * @param name
	 *            类名
	 * @param clazz
	 *            类class
	 * @return 对象实体
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		try {
			return getBean(name);
		} catch (Exception e) {
			try {
				return getBean(clazz);
			} catch (Exception ex) {
				return null;
			}
		}
	}

	/**
	 * @description 获取上下文实体集合
	 * @author xdwang
	 * @create 2015年11月22日上午10:31:51
	 * @version 1.0
	 * @return ApplicationBean集合
	 */
	public static Map<String, BeanDefinition> getApplicationBeanDefinitions() {
		Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();
		XmlWebApplicationContext context = (XmlWebApplicationContext) SpringApplicationContextHolder.getWebApplicationContext();
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		String[] names = factory.getBeanDefinitionNames();
		for (String name : names) {
			map.put(name, factory.getBeanDefinition(name));
		}
		return map;
	}

	/**
	 * @description 获取上下文实体集合
	 * @author xdwang
	 * @create 2015年11月22日上午10:31:51
	 * @version 1.0
	 * @return ApplicationBean集合
	 */
	public static Map<String, BeanDefinition> getDispatcherBeanDefinitions() {
		Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();
		XmlWebApplicationContext context = (XmlWebApplicationContext) SpringDispatcherContextHolder.getWebApplicationContext();
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		String[] names = factory.getBeanDefinitionNames();
		for (String name : names) {
			map.put(name, factory.getBeanDefinition(name));
		}
		return map;
	}

}
