package com.bgfang.material.service;

import com.bgfang.material.condition.OrderCondition;
import com.bgfang.material.entity.domain.OrdersDomain;
import com.bgfang.material.entity.mapper.OrdersDomainMapper;
import com.bgfang.material.entity.vo.OrderProductVo;
import com.bgfang.material.entity.vo.OrderVo;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.IdWorker;
import com.bgfang.material.util.ResultMap;
import com.bgfang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by bgfang on 2017/4/21.
 */
@Service
public class OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrdersDomainMapper ordersDomainMapper;

    public ResultMap insertOrUpdate(OrdersDomain ordersDomain) {
        ResultMap resultMap = new ResultMap();

        int count;

        if (StringUtils.isEmpty(ordersDomain.getOrderId())) {
            String id = UUID.randomUUID().toString();
            String orderNo = String.valueOf(idWorker.nextId());
            ordersDomain.setOrderId(id);
            ordersDomain.setOrderNo(orderNo);

            count = ordersDomainMapper.insert(ordersDomain);
        } else {

            count = ordersDomainMapper.updateByPrimaryKeySelective(ordersDomain);
        }

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("");
        }

        return resultMap;

    }

    public List<OrderVo> getListByPager(OrderCondition condition) {
        List<OrderVo> orderVos = ordersDomainMapper.getListByPager(condition);
        setDynamicFields(orderVos);
        return orderVos;
    }

    public int getListCount(OrderCondition condition) {
        return ordersDomainMapper.getListCount(condition);
    }

    private void setDynamicFields(List<OrderVo> orderVos) {
        for (OrderVo orderVo : orderVos) {
            String productNames = "";
            double totalPrice = 0;
            List<OrderProductVo> orderProductVos = orderVo.getOrderProductVos();
            boolean first = true;
            for (OrderProductVo orderProductVo : orderProductVos) {
                if (first) {
                    first = false;
                } else {
                    productNames += ",";
                }

                //计算总价
                totalPrice += orderProductVo.getProduct().getUnitPrice() * orderProductVo.getQuantity();
                productNames += orderProductVo.getProduct().getProductName();
            }

            orderVo.setProductNames(productNames);
            orderVo.setTotalPrice(totalPrice);
        }
    }

    public int updateDeleteStatus(String id, boolean isDelete) {
        return ordersDomainMapper.updateDeleteStatus(id, isDelete);
    }

    public int insert(OrdersDomain record) {
        return ordersDomainMapper.insert(record);
    }

    public int insertSelective(OrdersDomain record) {
        return ordersDomainMapper.insertSelective(record);
    }

    public OrdersDomain selectByPrimaryKey(String id) {
        return ordersDomainMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrdersDomain record) {
        return ordersDomainMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrdersDomain record) {
        return ordersDomainMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(String id) {
        return ordersDomainMapper.deleteByPrimaryKey(id);
    }
}
