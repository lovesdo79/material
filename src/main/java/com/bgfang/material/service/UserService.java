package com.bgfang.material.service;

import com.bgfang.material.condition.UserCondition;
import com.bgfang.material.entity.domain.UserDomain;
import com.bgfang.material.entity.mapper.UserDomainMapper;
import com.bgfang.material.util.Const;
import com.bgfang.material.util.ResultMap;
import com.bgfang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by bgfang on 2017/4/25.
 */
@Service
public class UserService {

    @Autowired
    UserDomainMapper userDomainMapper;

    /**
     * 新增/更新用户信息
     *
     * @param userDomain 用户
     * @return 结果
     */
    public ResultMap insertOrUpdate(UserDomain userDomain) {
        ResultMap resultMap = new ResultMap();

        int count = -1;
        String id = userDomain.getUserId();
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
            userDomain.setUserId(id);
            userDomain.setCreateTime(new Date());

            count = userDomainMapper.insertSelective(userDomain);
        } else {
            userDomain.setUpdateTime(new Date());

            count = userDomainMapper.updateByPrimaryKeySelective(userDomain);
        }

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("新增/更新用户信息失败！");
        }

        return resultMap;
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return 删除用户结果
     */
    public ResultMap delete(String userId) {
        ResultMap resultMap = new ResultMap();

        int count = userDomainMapper.virtualDelete(userId);
        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("删除用户失败！");
        }

        return resultMap;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户
     */
    public UserDomain selectByUserName(String userName) {
        return userDomainMapper.selectByUserName(userName);
    }

    public List<UserDomain> getListByPager(UserCondition condition) {
        List<UserDomain> userDomains = userDomainMapper.getList(condition);

        setDynamicFields(userDomains);

        return userDomains;
    }

    private void setDynamicFields(List<UserDomain> userDomains) {
    }

}
