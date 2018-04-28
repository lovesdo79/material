package com.fang.material.util.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fang.material.entity.domain.UserDomain;
import com.fang.material.util.Const;
import com.fang.material.util.JwtToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    private static final Log logger = LogFactory.getLog(TokenInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");

        try {
            if (!StringUtils.isEmpty(token)) {
                UserDomain userDomain = JwtToken.unsign(token, UserDomain.class);
                String userId = request.getParameter("userId");
                if (null != userDomain && !StringUtils.isEmpty(userId) && userId.equals(userDomain.getUserId())) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("Wrong token!" + e);
            JSONObject fail = new JSONObject();
            fail.put(Const.RTN_CODE, Const.TOKEN_ERROR);
            fail.put(Const.RTN_MSG, e.getMessage());
            PrintWriter out = response.getWriter();
            out.print(fail);
            out.flush();
            out.close();
            return false;
        }
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
