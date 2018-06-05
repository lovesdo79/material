package com.fang.material.controller;

import com.alibaba.fastjson.JSONObject;
import com.fang.material.condition.CustomerCondition;
import com.fang.material.entity.domain.CustomerDomain;
import com.fang.material.entity.vo.CustomerVo;
import com.fang.material.entity.vo.EasyUI;
import com.fang.material.entity.vo.ListResult;
import com.fang.material.service.CustomerService;
import com.fang.material.util.Const;
import com.fang.material.util.ResultMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fang on 2017/4/13.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    private final Log log = LogFactory.getLog(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/showList")
    public Object view() {
        return "customer/list";
    }

    @RequestMapping(value = {"/edit", "/edit/{id}"})
    public Object show(@PathVariable String id, HttpServletRequest request) {
        boolean islook = ServletRequestUtils.getBooleanParameter(request, "islook", false);
        boolean fastadd = ServletRequestUtils.getBooleanParameter(request, "fastadd", false);
//        boolean id = ServletRequestUtils.getBooleanParameter(request, "id", false);

        CustomerDomain customerDomain = customerService.selectByPrimaryKey(id);

        request.setAttribute("customer", customerDomain);
        request.setAttribute("islook", islook);
        request.setAttribute("fastadd", fastadd);

        return "customer/edit";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestBody CustomerCondition condition) {
        log.info("Receive request for[customer/list] with param:" + JSONObject.toJSONString(condition));
        filterCondition(condition);

        EasyUI<CustomerVo> easyUI = new EasyUI<>();

        List<CustomerVo> customers = customerService.getListByPager(condition);
        int total = customerService.getListCount(condition);
        easyUI.setRows(customers);
        easyUI.setTotal(total);

        return easyUI;
    }

    @RequestMapping("/custList")
    @ResponseBody
    public Object custList(CustomerCondition condition) {
        log.info(JSONObject.toJSONString(condition));

        filterCondition(condition);

        ListResult<CustomerVo> result = new ListResult<>();
        try {
            List<CustomerVo> customers = customerService.getListByPager(condition);
            int total = customerService.getListCount(condition);
            result.setRows(customers);
            result.setTotal(total);
        } catch (Exception e) {
            result.setRtnCode(Const.FAIL);
            result.setRtnMsg(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = {"/save"})
    @ResponseBody
    public Object save(CustomerDomain customerDomain) {
        ResultMap resultMap = customerService.insertOrUpdate(customerDomain);
        return resultMap;
    }

    @RequestMapping("del/{id}")
    @ResponseBody
    public Object delete(@PathVariable String id) {
        ResultMap resultMap = customerService.delete(id);

        return resultMap;
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll() {
        List<CustomerDomain> list = customerService.getAllList();

        ResultMap resultMap = new ResultMap(list);
        return resultMap;
    }
}
