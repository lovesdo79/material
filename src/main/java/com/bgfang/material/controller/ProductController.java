package com.bgfang.material.controller;

import com.bgfang.material.condition.ProductCondition;
import com.bgfang.material.entity.domain.ProductDomain;
import com.bgfang.material.entity.vo.EasyUI;
import com.bgfang.material.service.OrderProductService;
import com.bgfang.material.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bgfang on 2017/4/29.
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

}
