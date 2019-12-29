package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:02
 * @Version 1.0
 */
public interface CouponService {
    DataVo couponList(Map<String,String> map);

    Coupon couponCreate(Coupon coupon);

    Coupon couponRead(Integer id);

    Coupon couponUpdate(Coupon coupon);

    Integer couponDelete(Coupon coupon);

    List<Coupon> couponAllList() throws DbException;
    //-----------------------------------------------------------------------------------------------
    Map<String, Object> wxCouponList(Map<String, String> map);
}
