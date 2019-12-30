package com.jiaxingrong.controller.admin;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.CouponService;
import com.jiaxingrong.service.inter.CouponUserService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 优惠券管理
 * @Author:luchang
 * @Date: 2019/12/27 14:58
 * @Version 1.0
 */
@RestController
@RequestMapping("admin")
public class CouponController {
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;


    /**
     * 优惠券显示和搜索
     * @param map
     * @return
     */
    @RequestMapping("coupon/list")
    public ResultVo couponList(@RequestParam Map<String,String> map){
        DataVo couponList =couponService.couponList(map);
        return ResultVoTools.successRe(couponList, "显示成功");
    }

    /**
     * 优惠券添加
     * @param coupon
     * @return
     */
    @RequestMapping("coupon/create")
    public ResultVo couponCreate(@RequestBody Coupon coupon) throws AdEx {
        setLimit(coupon);
        Coupon couponCreate =couponService.couponCreate(coupon);
        if(couponCreate == null){
            return ResultVoTools.failedRe("添加失败");
        }
        return ResultVoTools.successRe(couponCreate, "添加成功");
    }

    /**
     * 优惠券详情
     * @param id
     * @return
     */
    @RequestMapping("coupon/read")
    public ResultVo couponRead(@RequestParam Integer id){
        Coupon couponRead = couponService.couponRead(id);
        return ResultVoTools.successRe(couponRead, "加载优惠券详情成功");
    }

    /**
     * 优惠券相关用户加载和搜索
     * @param map
     * @return
     */
    @RequestMapping("coupon/listuser")
    public ResultVo couponListuser(@RequestParam Map<String,String> map){
        DataVo couponUserDataVo = couponUserService.userListByCouponId(map);
        return ResultVoTools.successRe(couponUserDataVo, "加载优惠券相应用户成功");
    }

    /**
     * 优惠券编辑
     * @param coupon
     * @return
     */
    @RequestMapping("coupon/update")
    public ResultVo couponUpdate(@RequestBody Coupon coupon) throws AdEx {
        setLimit(coupon);
        Coupon coupon1 = couponService.couponUpdate(coupon);
        if(coupon1 == null){
            return ResultVoTools.failedRe("修改优惠券失败");
        }
        return ResultVoTools.successRe(coupon1, "修改优惠券成功");
    }

    /**
     * 优惠券删除，相关信息删除
     * @param coupon
     * @return
     */
    @RequestMapping("coupon/delete")
    public ResultVo couponDelete(@RequestBody Coupon coupon){
        Integer couponDelete = couponService.couponDelete(coupon);
        Integer couponUserDelete = couponUserService.couponUserDelete(coupon);
        if(couponDelete == 1) {
            return ResultVoTools.successRe(couponDelete, "删除成功,另外相关删除:" + couponUserDelete+"个");
        } else{
            return ResultVoTools.failedRe("删除失败");
        }
    }

    public void setLimit(Coupon coupon) throws AdEx{
        if(coupon.getDiscount().intValue() < 0){
            throw new AdEx("折扣金额不能为负");
        }
        if(coupon.getMin().intValue() < 0){
            throw new AdEx("最低消费不能为负");
        }
        //coupon.vue目录mall-admin\src\views\promotion
        //将来使用到数量为1时不能变零，特别提醒
        if(coupon.getLimit() < 0){
            throw new AdEx("最低领取数量不能为负,0代表不限制");
        }
        if(coupon.getDays() < 1 && coupon.getTimeType() == 0){
            throw new AdEx("有效天数不能小于1");
        }
        if(coupon.getTotal() < 0){
            throw new AdEx("优惠券数量不能为负，0代表不限制");
        }
        if(coupon.getDiscount().intValue() > coupon.getMin().intValue()){
            throw new AdEx("折扣金额大于消费门槛？");
        }
        if(coupon.getTimeType() == 1 && coupon.getStartTime().after(coupon.getEndTime())){
            throw new AdEx("起始时间超过结束时间？");
        }
    }
}
