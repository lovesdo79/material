package com.fang.material.service;

import com.fang.material.condition.UserCondition;
import com.fang.material.entity.domain.UserDomain;
import com.fang.material.entity.mapper.UserDomainMapper;
import com.fang.material.util.Const;
import com.fang.material.util.ResultMap;
import com.fang.material.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by fang on 2017/4/25.
 */
@Service
public class UserService {

    @Autowired
    UserDomainMapper userDomainMapper;

    @Value("${material.default.password}")
    private String defaultPassword;

    /**
     * 新增/更新用户信息
     *
     * @param userDomain 用户
     * @return 结果
     */
    public ResultMap insert(UserDomain userDomain) {
        ResultMap resultMap = new ResultMap();

        //不允许重复用户名
        UserDomain user = this.selectByUserName(userDomain.getUserName());
        if (null != user) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("用户名已存在！");
            return resultMap;
        }

        String id = UUID.randomUUID().toString();
        userDomain.setUserId(id);
        userDomain.setCreateTime(new Date());
        userDomain.setUpdateTime(new Date());

        String password = defaultPassword;
        if (StringUtils.isEmpty(password)) {
            password = userDomain.getUserName();
        }
        userDomain.setPasswd(password);


        int count = userDomainMapper.insertSelective(userDomain);

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("新增用户信息失败！");
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

    public ResultMap resetPassword(String userId) {
        ResultMap resultMap = new ResultMap();

        UserDomain userDomain = this.selectByPrimaryKey(userId);

        String password = defaultPassword;
        if (StringUtils.isEmpty(password)) {
            password = userDomain.getUserName();
        }
        userDomain.setPasswd(password);
        userDomain.setUpdateTime(new Date());

        int count = this.updateByPrimaryKeySelective(userDomain);

        if (count <= 0) {
            resultMap.setRtnCode(Const.FAIL);
            resultMap.setRtnMsg("重置密码失败！");
        } else {
            resultMap.setRtnMsg("重置密码成功！");
        }

        return resultMap;
    }

    public UserDomain selectByPrimaryKey(String userId) {
        return userDomainMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(UserDomain userDomain) {
        return userDomainMapper.updateByPrimaryKeySelective(userDomain);
    }

    public List<UserDomain> getListByPager(UserCondition condition) {
        List<UserDomain> userDomains = userDomainMapper.getList(condition);

        setDynamicFields(userDomains);

        return userDomains;
    }

    public int getListCount(UserCondition condition) {
        return userDomainMapper.getListCount(condition);
    }

    private void setDynamicFields(List<UserDomain> userDomains) {
    }

}
