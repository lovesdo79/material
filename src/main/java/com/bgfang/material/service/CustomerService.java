package com.bgfang.material.service;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.entity.domain.CustomerDomain;
import com.bgfang.material.entity.mapper.CustomerDomainMapper;
import com.bgfang.material.entity.vo.CustomerVo;
import com.bgfang.material.enums.CustomerType;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.ResultMap;
import com.bgfang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by bgfang on 2017/4/13.
 */
@Service
public class CustomerService extends BaseService<CustomerDomain, CustomerDomainMapper> {

    @Autowired
    private CustomerDomainMapper customerDomainMapper;

    public ResultMap insertOrUpdate(CustomerDomain customerDomain) {
        ResultMap resultMap = new ResultMap();

        if (null == customerDomain) {
            resultMap.setRtnMsg("客户信息为空，不能新增/修改！");
            return resultMap;
        }

        int count = 0;
        if (null == customerDomain.getCustomerId() || "".equalsIgnoreCase(customerDomain.getCustomerId())) {
            String id = UUID.randomUUID().toString();
            customerDomain.setCustomerId(id);
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
        setDynamicFields(customerVos);
        return customerVos;
    }

    private void setDynamicFields(List<CustomerVo> customerVos) {
        for (CustomerVo customerVo : customerVos) {
            //设置客户类型
            String customerType = CustomerType.getValue(customerVo.getType());
            customerVo.setCustomerType(customerType);
        }
    }

    public int getListCount(CustomerCondition condition) {
        return customerDomainMapper.getListCount(condition);
    }

    public ResultMap delete(String id) {
        ResultMap resultMap = new ResultMap();
        if (StringUtils.isEmpty(id)) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("请选择需要删除的客户!");
            return resultMap;
        }

        int count = this.updateDeleteStatus(id, true);

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("您要删除的客户不存在!");
        }
        return resultMap;
    }

    public int updateDeleteStatus(String id, boolean isDelete) {
        return customerDomainMapper.updateDeleteStatus(id, isDelete);
    }

    public int insert(CustomerDomain record) {
        return customerDomainMapper.insert(record);
    }

    public int insertSelective(CustomerDomain record) {
        return customerDomainMapper.insertSelective(record);
    }

    public CustomerDomain selectByPrimaryKey(String id) {
        return customerDomainMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CustomerDomain record) {
        return customerDomainMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerDomain record) {
        return customerDomainMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(String id) {
        return customerDomainMapper.deleteByPrimaryKey(id);
    }
}
