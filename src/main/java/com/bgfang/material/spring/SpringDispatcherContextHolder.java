package com.bgfang.material.spring;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @description 存放DispatcherSerlvlet执行的上下文
 * @author xdwang
 * @create 2015年11月22日上午10:25:34
 * @version 1.0
 * @see RequestContextHolder
 */
public class SpringDispatcherContextHolder {

	/**
	 * ThreadLocal<HttpServletResponse>
	 */
	private static final ThreadLocal<HttpServletResponse> RESPONSEHOLDER = new NamedThreadLocal<HttpServletResponse>("Response Holder");

	/**
	 * @description 初始化
	 * @author xdwang
	 * @create 2015年11月22日上午10:27:05
	 * @version 1.0
	 * @param response
	 *            HttpServletResponse对象
	 */
	public static void initDispatcherContext(HttpServletResponse response) {
		if (response != null) {
			RESPONSEHOLDER.set(response);
		}
	}

	/**
	 * @description 重置
	 * @author xdwang
	 * @create 2015年11月22日上午10:27:27
	 * @version 1.0
	 */
	public static void resetDispatcherContext() {
		RESPONSEHOLDER.remove();
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse getResponse() {
		return RESPONSEHOLDER.get();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	/**
	 * @description 获取DispathcerSerlvet的WebApplicationContext，而非全局的
	 * @author xdwang
	 * @create 2015年11月22日上午10:26:24
	 * @version 1.0
	 * @return WebApplicationContext对象
	 */
	public static WebApplicationContext getWebApplicationContext() {
		return RequestContextUtils.getWebApplicationContext(getRequest());
	}

	/**
	 * @description 根据名字获取对象
	 * @author xdwang
	 * @create 2015年11月22日上午10:28:31
	 * @version 1.0
	 * @param <T>
	 *            泛型
	 * @param name
	 *            类名
	 * @return 对象实体
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) getWebApplicationContext().getBean(name);
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
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		return (T) getWebApplicationContext().getBean(clazz);
	}
}
