package com.bgfang.material.service;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.entity.domain.CustomerDomain;
import com.bgfang.material.entity.mapper.CustomerDomainMapper;
import com.bgfang.material.entity.vo.CustomerVo;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by bgfang on 2017/4/13.
 */
@Service
public class CustomerService extends BaseService<CustomerDomain,CustomerDomainMapper>{

    @Autowired
    private CustomerDomainMapper customerDomainMapper;

    public ResultMap insertOrUpdate(CustomerDomain customerDomain) {
        ResultMap resultMap = new ResultMap();

        if (null == customerDomain) {
            resultMap.setRtnMsg("客户信息为空，不能新增/修改！");
            return resultMap;
        }

        int count = 0;
        if (null == customerDomain.getId() || "".equalsIgnoreCase(customerDomain.getId())) {
            String id = UUID.randomUUID().toString();
            customerDomain.setId(id);
            customerDomain.setCreateTime(new Date());
            customerDomain.setUpdateTime(new Date());
            count = customerDomainMapper.insert(customerDomain);
        } else {
            customerDomain.setUpdateTime(new Date());
            count = customerDomainMapper.updateByPrimaryKeySelective(customerDomain);
        }

        if (count < 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("新增/修改数据失败！");
        }
        return resultMap;
    }


    public List<CustomerVo> getListByPager(CustomerCondition condition) {
        List<CustomerVo> customerVos = customerDomainMapper.getListByPager(condition);
        return customerVos;
    }

    public int getListCount(CustomerCondition condition) {
        return customerDomainMapper.getListCount(condition);
    }
}
