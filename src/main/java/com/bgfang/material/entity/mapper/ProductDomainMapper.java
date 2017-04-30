package com.bgfang.material.entity.mapper;

import com.bgfang.material.entity.domain.ProductDomain;
import com.bgfang.material.entity.vo.ProductVo;

import java.util.List;

/**
 * Created by bgfang on 2017/4/24.
 */
public interface ProductDomainMapper extends BaseMapper<ProductDomain> {

    int insertBatch(List<ProductVo> list);

    List<ProductDomain> selectListByOrderId(String orderId);
}
