package com.bgfang.material.controller;

import com.bgfang.material.condition.BaseCondition;
import com.bgfang.material.enums.SortName;

/**
 * Created by bgfang on 2017/4/15.
 */
public class BaseController {

    public void filterCondition(BaseCondition conditon) {

        String sort = conditon.getSort();

        sort = SortName.getValue(sort);

        conditon.setSort(sort);

    }
}
