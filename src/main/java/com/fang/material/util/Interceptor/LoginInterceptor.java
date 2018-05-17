package com.fang.material.util.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fang.material.entity.domain.UserDomain;
import com.fang.material.util.Const;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    //    private final Log logger = LogFactory.getLog(LoginInterceptor.class);
    private static final Log log = LogFactory.getLog(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        log.info("Request URL:" + url);
//        try {
//            //静态资源允许直接访问
//            HttpSession session = request.getSession();
//            UserDomain userDomain = (UserDomain) session.getAttribute("user");
//            if (null == userDomain) {
//                PrintWriter printWriter = response.getWriter();
//                printWriter.print("<script language>alert('请登录！…………');top.location.href='index.html'</script>");
//                response.sendRedirect(request.getContextPath() + "/index.html");
//                return false;
//            }
//        } catch (Exception e) {
//            log.error("Login Interceptor Error!" + e);
//            JSONObject fail = new JSONObject();
//            fail.put(Const.RTN_CODE, Const.FAIL);
//            fail.put(Const.RTN_MSG, e.getMessage());
//            PrintWriter out = response.getWriter();
//            out.print(fail);
//            out.flush();
//            out.close();
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
