package com.bgfang.material.entity.mapper;

import com.bgfang.material.entity.domain.CurtainsDomain;

public interface CurtainsDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    int insert(CurtainsDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    int insertSelective(CurtainsDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    CurtainsDomain selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    int updateByPrimaryKeySelective(CurtainsDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CURTAINS
     *
     * @mbggenerated Fri Apr 14 16:35:32 CST 2017
     */
    int updateByPrimaryKey(CurtainsDomain record);
}