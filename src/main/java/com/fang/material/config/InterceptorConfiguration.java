package com.fang.material.config;

import com.fang.material.util.Interceptor.LoginInterceptor;
import com.fang.material.util.Interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration login = registry.addInterceptor(new LoginInterceptor());
        // 配置不拦截的路径
        List<String> exludePathPatrerns = new ArrayList<>();
//        exludePathPatrerns.add("/base/getToken");
//        exludePathPatrerns.add("/**/wechat");
//        exludePathPatrerns.add("/resources/**");
        exludePathPatrerns.add("/**/logout");
        exludePathPatrerns.add("/**/login");
//        exludePathPatrerns.add("/**.jsp");
        exludePathPatrerns.add("/**.html");
        exludePathPatrerns.add("/js/**");
        exludePathPatrerns.add("/**.css");
//        exludePathPatrerns.add("/static/**");
        exludePathPatrerns.add("/error");
//        exludePathPatrerns.add("/index");
//        exludePathPatrerns.add("/templates/**");
        login.excludePathPatterns(exludePathPatrerns);
        login.addPathPatterns("/**");


        // 注册拦截器
//        InterceptorRegistration ir = registry.addInterceptor(new TokenInterceptor());
        // 配置拦截的路径
//        ir.addPathPatterns("/**/wechat");
    }
}
