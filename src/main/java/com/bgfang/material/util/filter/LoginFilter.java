package com.bgfang.material.util.filter;

import com.bgfang.material.entity.domain.UserDomain;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bgfang on 2017/4/25.
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();

        //静态资源允许直接访问


        HttpSession session = request.getSession();
        UserDomain userDomain = (UserDomain) session.getAttribute("user");
        if (url.contains("/resources/") || url.endsWith("logout") || url.endsWith("login.jsp") || url.endsWith("login") || url.endsWith("wechat") || url.endsWith("getToken") || null != userDomain) {
            filterChain.doFilter(request, response);
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script language>alert('请登录！…………');top.location.href='login.jsp'</script>");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    public void destroy() {

    }
}
