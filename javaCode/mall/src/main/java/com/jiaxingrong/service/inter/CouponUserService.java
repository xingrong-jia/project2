package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:05
 * @Version 1.0
 */
public interface CouponUserService {
    DataVo userListByCouponId(Map<String,String> map);

    Integer couponUserDelete(Coupon coupon);
    //-----------------------------------------------------------------------------------------------
    Integer wxCouponExchange(Coupon coupon) throws AdEx;

    Map<String, Object> wxCouponMylist(Map<String, String> map);

    List<Coupon> wxCouponSelectlist(Map<String, String> map);

    Integer wxCouponReceive(Integer couponId) throws AdEx;
}

