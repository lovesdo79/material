package com.bgfang.material.controller;

import com.bgfang.material.condition.OrderCondition;
import com.bgfang.material.entity.domain.OrdersDomain;
import com.bgfang.material.entity.mapper.OrdersDomainMapper;
import com.bgfang.material.entity.vo.EasyUI;
import com.bgfang.material.entity.vo.OrderVo;
import com.bgfang.material.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bgfang on 2017/4/21.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/showList")
    public String showList() {
        return "order/list";
    }

    @RequestMapping("show/{id}")
    public String show(@PathVariable String id) {
        OrdersDomain ordersDomain = orderService.selectByPrimaryKey(id);
        return "order/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(OrderCondition condition) {
        EasyUI<OrderVo> easyUI = new EasyUI<OrderVo>();
        filterCondition(condition);

        List<OrderVo> orderVos = orderService.getListByPager(condition);
        int total = orderService.getListCount(condition);
        easyUI.setRows(orderVos);
        easyUI.setTotal(total);

        return easyUI;
    }


}
