package com.bgfang.material.controller;

import com.bgfang.material.condition.BaseCondition;
import com.bgfang.material.enums.ProductType;
import com.bgfang.material.enums.SortName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.EnumSet;

/**
 * Created by bgfang on 2017/4/15.
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    @RequestMapping("/listType/{type}")
    @ResponseBody
    public Object listType(@PathVariable("type") String type) {
        if ("product".equalsIgnoreCase(type)) {
            return ProductType.getAll();
        }

        return null;
    }

    public void filterCondition(BaseCondition conditon) {

        String sort = conditon.getSort();

        sort = SortName.getValue(sort);

        conditon.setSort(sort);

    }

    public boolean checkSafe(String userId, String token) {
        return false;
    }

    public String getToken() {
        return null;
    }
}
