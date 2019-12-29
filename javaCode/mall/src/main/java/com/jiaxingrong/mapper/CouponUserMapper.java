package com.jiaxingrong.mapper;

import com.jiaxingrong.bean.example.CouponUserExample;
import com.jiaxingrong.bean.type.CouponUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/27 19:56
 * @Version 1.0
 */

public interface CouponUserMapper {
    long countByExample(CouponUserExample example);

    int deleteByExample(CouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    List<CouponUser> selectByExample(CouponUserExample example);

    CouponUser selectByPrimaryKey(Integer id);
    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByExample(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);

    Integer selectCouponNum(@Param("userId") Integer userId, @Param("goodsTotalPrice") Double goodsTotalPrice);
}
