package com.fang.material.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fang.material.condition.ProductCondition;
import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.vo.EasyUI;
import com.fang.material.entity.vo.ListResult;
import com.fang.material.entity.vo.ProductVo;
import com.fang.material.service.OrderProductService;
import com.fang.material.service.ProductService;
import com.fang.material.util.Const;
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
    public Object list(@RequestBody ProductCondition condition) {
        filterCondition(condition);

        ListResult<ProductVo> result = new ListResult<>();
        try {
            List<ProductVo> productDomains = productService.getListByPager(condition);
            int total = productService.getListCount(condition);
            result.setTotal(total);
            result.setRows(productDomains);
        } catch (Exception e) {
            result.setRtnCode(Const.FAIL);
            result.setRtnMsg(e.getMessage());
        }

        return result;
    }

}