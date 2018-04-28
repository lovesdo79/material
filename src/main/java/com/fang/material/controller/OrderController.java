package com.fang.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fang.material.condition.OrderCondition;
import com.fang.material.entity.domain.CustomerDomain;
import com.fang.material.entity.domain.OrderProductDomain;
import com.fang.material.entity.domain.OrdersDomain;
import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.vo.EasyUI;
import com.fang.material.entity.vo.OrderVo;
import com.fang.material.entity.vo.ProductVo;
import com.fang.material.service.CustomerService;
import com.fang.material.service.OrderProductService;
import com.fang.material.service.OrderService;
import com.fang.material.service.ProductService;
import com.fang.material.util.IdWorker;
import com.fang.material.util.ResultMap;
import com.fang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by fang on 2017/4/21.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping("/showList")
    public String showList() {
        return "order/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, HttpServletRequest request) {
        OrdersDomain ordersDomain = orderService.selectByPrimaryKey(id);

        boolean isLook = ServletRequestUtils.getBooleanParameter(request, "isLook", false);

        request.setAttribute("isLook", isLook);
        request.setAttribute("order", ordersDomain);
        if (null != ordersDomain) {
            CustomerDomain customerDomain = customerService.selectByPrimaryKey(ordersDomain.getCustomerId());
            request.setAttribute("customer", customerDomain);
        }

        List<OrderProductDomain> orderProductDomains = orderProductService.selectListByOrderId(id);
        List<ProductDomain> productDomains = new ArrayList<ProductDomain>();
        for (OrderProductDomain orderProductDomain : orderProductDomains) {
            ProductDomain productDomain = productService.selectByPrimaryKey(orderProductDomain.getProductId());

            productDomains.add(productDomain);
        }
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

    @RequestMapping("/save")
    @ResponseBody
    public Object save(CustomerDomain customerDomain, OrdersDomain ordersDomain, String products, HttpServletRequest request) {
        ResultMap resultMap = new ResultMap();
        JSONArray productArray = new JSONArray();
        List<ProductVo> productVos = new ArrayList<ProductVo>();
        if (!StringUtils.isEmpty(products)) {
            productArray = JSONArray.parseArray(products);
            productVos = productArray.toJavaList(ProductVo.class);
        }

        //保存客户信息
        String customerId = customerDomain.getCustomerId();
        if (StringUtils.isEmpty(customerId)) {
            customerId = UUID.randomUUID().toString();
            customerService.insert(customerDomain);
            ordersDomain.setCustomerId(customerId);
        }

        //生成订单
//        OrdersDomain ordersDomain = new OrdersDomain();
        ordersDomain.setOrderNo(String.valueOf(idWorker.nextId()));
        resultMap = orderService.insertOrUpdate(ordersDomain);

        String orderId = resultMap.getRtnMsg();

        List<OrderProductDomain> orderProductDomains = new ArrayList<OrderProductDomain>();
        for (ProductVo productVo : productVos) {
            if (StringUtils.isEmpty(productVo.getProductId())) {
                productVo.setProductId(UUID.randomUUID().toString());
            }
            productVo.setIsDelete(false);

            OrderProductDomain orderProductDomain = new OrderProductDomain();
            orderProductDomain.setOrderProductId(UUID.randomUUID().toString());
            orderProductDomain.setOrderId(orderId);
            orderProductDomain.setProductId(productVo.getProductId());
            orderProductDomain.setQuantity(productVo.getQuantity());
            orderProductDomain.setValuationMethod(productVo.getValuationMethod());

            orderProductDomains.add(orderProductDomain);
        }

        //保存产品
        int productResult = productService.insertBatch(productVos);
        if (productResult != productVos.size()) {

        }

        //产品订单关系保存
        int orderProductResult = orderProductService.insertBatch(orderProductDomains);
        if (orderProductResult == orderProductDomains.size()) {

        }

        return resultMap;
    }


    @RequestMapping("/list/wechat")
    @ResponseBody
    public Object listWechat(OrderCondition condition) {
        Object result = this.list(condition);

        String token = condition.getToken();

        JSONObject data = getResultData(token);
        data.putAll(JSONObject.parseObject(JSONObject.toJSONString(result)));

        ResultMap resultMap = new ResultMap(data);

        return resultMap;
    }
}
