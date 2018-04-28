package com.fang.material.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fang.material.condition.ProductCondition;
import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.vo.EasyUI;
import com.fang.material.service.OrderProductService;
import com.fang.material.service.ProductService;
import com.fang.material.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2017/4/29.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id) {

        return "product/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(ProductCondition condition) {
        filterCondition(condition);

        EasyUI<ProductDomain> easyUI = new EasyUI<ProductDomain>();
        List<ProductDomain> productDomains = productService.selectListByOrderId(condition.getOrderId());
        easyUI.setTotal(productDomains.size());
        easyUI.setRows(productDomains);

        return easyUI;
    }


    @RequestMapping("/list/wechat")
    @ResponseBody
    public Object listWechat(ProductCondition condition) {
        Object result = this.list(condition);

        String token = condition.getToken();

        JSONObject data = getResultData(token);
        data.putAll(JSONObject.parseObject(JSONObject.toJSONString(result)));

        ResultMap resultMap = new ResultMap(data);

        return resultMap;
    }


}
