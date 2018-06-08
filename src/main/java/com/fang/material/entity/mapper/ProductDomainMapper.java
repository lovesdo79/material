package com.fang.material.entity.mapper;

import com.fang.material.condition.ProductCondition;
import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fang on 2017/4/24.
 */
@Mapper
public interface ProductDomainMapper extends BaseMapper<ProductDomain> {

    int insertBatch(List<ProductVo> list);

    List<ProductDomain> selectListByOrderId(String orderId);

    List<ProductVo> getListByPager(ProductCondition condition);

    int getListCount(ProductCondition condition);
}
