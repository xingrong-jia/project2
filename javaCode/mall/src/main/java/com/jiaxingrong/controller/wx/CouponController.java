package com.jiaxingrong.controller.wx;

import com.jiaxingrong.bean.type.CouponUser;
import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.service.inter.CouponService;
import com.jiaxingrong.service.inter.CouponUserService;
import com.jiaxingrong.tools.ResultVoTools;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/27 16:14
 * @Version 1.0
 */
@RestController(value = "wxCoupon")
@RequestMapping("wx")
public class CouponController {
    @Autowired
    CouponUserService couponUserService;
    @Autowired
    CouponService couponService;

    /**
     * 优惠券领取
     * 通用类型-兑换类型-注册类型
     * 领取-先查再写
     * HttpSession httpSession,HttpServerletRequest httpserverletrequest
     * @param couponUser
     * @return
     */
    @RequestMapping("coupon/receive")
    @RequiresAuthentication
    public ResultVo couponReceive(@RequestBody CouponUser couponUser) throws AdEx {
        Integer couponReceive = couponUserService.wxCouponReceive(couponUser.getCouponId());
        if(couponReceive == 1){
            return ResultVoTools.successRe(null,"领取优惠券成功");
        }
        return ResultVoTools.successRe(740,"优惠券已领取过");
    }

    /**
     * 优惠券兑换
     * @param
     * @return
     */
    @RequestMapping("coupon/exchange")
    @RequiresAuthentication
    public ResultVo couponExchange(@RequestBody Coupon coupon) throws AdEx {
        Integer couponExchange = couponUserService.wxCouponExchange(coupon);
        if(couponExchange == 1){
            return ResultVoTools.successRe(couponExchange,"兑换优惠券成功");
        }
        return ResultVoTools.successRe(740,"优惠券已兑换过");
    }

    @RequestMapping("coupon/list")
    public ResultVo couponList(@RequestParam Map<String,String> map){
        Map<String,Object> couponList = couponService.wxCouponList(map);
        return ResultVoTools.successRe(couponList,"加载优惠券列表成功");
    }

    /**
     * 我的优惠券
     * @param map
     * @return
     */
    @RequestMapping("coupon/mylist")
    @RequiresAuthentication
    public ResultVo couponMylist(@RequestParam Map<String,String> map){
        Map<String,Object> couponMylist = couponUserService.wxCouponMylist(map);
        if(map.get("status").equals("0")){
            return ResultVoTools.successRe(couponMylist,"加载未使用优惠券列表成功");
        }else if(map.get("status").equals("1")){
            return ResultVoTools.successRe(couponMylist, "加载已使用优惠券列表成功");
        }else{
            return ResultVoTools.successRe(couponMylist,"加载已过期优惠券列表成功");
        }
    }

    /**
     * 可用优惠券
     * @param map
     * @return
     */
    @RequestMapping("coupon/selectlist")
    @RequiresAuthentication
    public ResultVo couponSelectlist(@RequestParam Map<String,String> map){
        List<Coupon> couponSelectlist = couponUserService.wxCouponSelectlist(map);
        return ResultVoTools.successRe(couponSelectlist,"加载可用优惠券列表成功");
    }
}
