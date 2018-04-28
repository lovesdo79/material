package com.fang.material.controller;

import com.fang.material.condition.UserCondition;
import com.fang.material.entity.domain.UserDomain;
import com.fang.material.entity.vo.EasyUI;
import com.fang.material.service.UserService;
import com.fang.material.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fang on 2017/4/25.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showList")
    public Object showList() {
        return "user/list";
    }

    @RequestMapping("/show")
    public Object show() {
        return "user/add";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(UserCondition condition) {
        filterCondition(condition);

        EasyUI<UserDomain> easyUI = new EasyUI<UserDomain>();
        List<UserDomain> userDomains = userService.getListByPager(condition);
        int total = userService.getListCount(condition);

        easyUI.setTotal(total);
        easyUI.setRows(userDomains);

        return easyUI;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(UserDomain userDomain) {
        ResultMap resultMap = userService.insert(userDomain);

        return resultMap;
    }

    @RequestMapping("/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") String id) {
        ResultMap resultMap = userService.delete(id);

        return resultMap;
    }

    @RequestMapping("/reset/{id}")
    @ResponseBody
    public Object reset(@PathVariable("id") String id) {
        ResultMap resultMap = userService.resetPassword(id);

        return resultMap;
    }
}
