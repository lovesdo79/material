package com.bgfang.material.controller;

import com.bgfang.material.entity.domain.UserDomain;
import com.bgfang.material.service.UserService;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.ResultMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bgfang on 2017/4/24.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@Param("userName") String userName, @Param("passwd") String passwd, HttpServletRequest request) {
        ResultMap resultMap = new ResultMap();

        UserDomain userDomain = userService.selectByUserName(userName);

        if (null != userDomain && userDomain.getPasswd().equals(passwd)) {
            request.getSession().setAttribute("user", userDomain);
//            response.sendRedirect(redirect);
        } else {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("用户名/密码错误!");
        }
        return resultMap;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");

        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }


}
