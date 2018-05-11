package com.bgfang.material.util.filter;

import com.alibaba.fastjson.JSONObject;
import com.bgfang.material.entity.domain.UserDomain;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.JwtToken;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");


        if (!StringUtils.isEmpty(token)) {
            UserDomain userDomain = JwtToken.unsign(token, UserDomain.class);
            String userId = request.getParameter("userId");
            if (null != userDomain && !StringUtils.isEmpty(userId) && userId.equals(userDomain.getUserId())) {
                return true;
            }
        }

        JSONObject fail = new JSONObject();
        fail.put(Const.RTN_CODE, Const.FAIL);
        fail.put(Const.RTN_MSG, "token验证失败");

        PrintWriter out = response.getWriter();
        out.print(fail);
        out.flush();
        out.close();

        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
