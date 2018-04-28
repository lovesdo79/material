package com.fang.material.controller;

import com.alibaba.fastjson.JSONObject;
import com.fang.material.condition.BaseCondition;
import com.fang.material.entity.domain.UserDomain;
import com.fang.material.enums.ProductType;
import com.fang.material.enums.SortName;
import com.fang.material.service.UserService;
import com.fang.material.util.Const;
import com.fang.material.util.JwtToken;
import com.fang.material.util.ResultMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fang on 2017/4/15.
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    private final Log log = LogFactory.getLog(BaseController.class);


    @Autowired
    private UserService userService;

    @RequestMapping("/listType/{type}")
    @ResponseBody
    public Object listType(@PathVariable("type") String type) {
        if ("product".equalsIgnoreCase(type)) {
            return ProductType.getAll();
        }

        return null;
    }

    public void filterCondition(BaseCondition conditon) {

        String sort = conditon.getSort();

        sort = SortName.getValue(sort);

        conditon.setSort(sort);

    }


    /**
     * 获取token
     *
     * @param userName
     * @param password
     * @return token
     */
    @RequestMapping("/getToken")
    @ResponseBody
    public Object getToken(String userName, String password) {
        ResultMap resultMap = new ResultMap();
        try {
            UserDomain userDomain = userService.selectByUserName(userName);
            if (null != userDomain && userDomain.getPasswd().equals(password)) {
                String token = JwtToken.sign(userDomain, 60L * 1000L * 30L);

                JSONObject data = new JSONObject();
                data.put(Const.TOKEN, token);
                resultMap.setData(data);
            } else {
                resultMap.setRtnCode(Const.FAIL);
                resultMap.setRtnMsg("wrong username or password!");
            }

        } catch (Exception e) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("create token error!");
            log.error("create token error!" + e);
        }
        return resultMap;
    }

    public JSONObject getResultData(String token) {
        JSONObject data = new JSONObject();
        try {
            UserDomain userDomain = JwtToken.unsign(token, UserDomain.class);
            String _token = JwtToken.sign(userDomain, 60L * 1000L * 30L);

            data.put(Const.TOKEN, _token);
        } catch (Exception e) {
            data.put(Const.TOKEN, token);
            log.error("update token error!" + e);
        }

        return data;
    }

}
