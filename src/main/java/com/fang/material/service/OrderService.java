package com.fang.material.service;

import com.fang.material.condition.OrderCondition;
import com.fang.material.entity.domain.OrdersDomain;
import com.fang.material.entity.mapper.OrdersDomainMapper;
import com.fang.material.entity.vo.OrderProductVo;
import com.fang.material.entity.vo.OrderVo;
import com.fang.material.util.Const;
import com.fang.material.util.IdWorker;
import com.fang.material.util.ResultMap;
import com.fang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by fang on 2017/4/21.
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

        String id = UUID.randomUUID().toString();
        if (StringUtils.isEmpty(ordersDomain.getOrderId())) {
            String orderNo = String.valueOf(idWorker.nextId());
            ordersDomain.setOrderId(id);
            ordersDomain.setOrderNo(orderNo);
            ordersDomain.setCreateTime(new Date());
            ordersDomain.setUpdateTime(new Date());

            count = ordersDomainMapper.insertSelective(ordersDomain);
        } else {

            ordersDomain.setUpdateTime(new Date());
            count = ordersDomainMapper.updateByPrimaryKeySelective(ordersDomain);
        }

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("");
        } else {
            resultMap.setRtnMsg(id);
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
