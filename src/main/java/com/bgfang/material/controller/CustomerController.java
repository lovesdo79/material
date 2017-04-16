package com.bgfang.material.controller;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.entity.domain.CustomerDomain;
import com.bgfang.material.entity.vo.CustomerVo;
import com.bgfang.material.entity.vo.EasyUI;
import com.bgfang.material.service.CustomerService;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.ResultMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bgfang on 2017/4/13.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    private final Log log = LogFactory.getLog(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/show_list.do")
    public Object view() {
        return "customer/list";
    }

    @RequestMapping("/list.do")
    @ResponseBody
    public Object list(CustomerCondition condition) {
        filterCondition(condition);

        EasyUI<CustomerVo> easyUI = new EasyUI<CustomerVo>();

        List<CustomerVo> customers = customerService.getListByPager(condition);
        int total = customerService.getListCount(condition);
        easyUI.setRows(customers);
        easyUI.setTotal(total);

        return easyUI;
    }

    @RequestMapping(value = {"/save.do"})
    @ResponseBody
    public Object insertOrUpdate(@RequestBody CustomerDomain customerDomain, HttpServletRequest request) {
        String name = ServletRequestUtils.getStringParameter(request, "name", "");
        ResultMap resultMap = customerService.insertOrUpdate(customerDomain);
        return resultMap;
    }
}