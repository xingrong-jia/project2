package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.mapper.*;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.model.CouponExample;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:20
 * @Version 1.0
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;

    //优惠券显示
    @Override
    public DataVo couponList(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer limit = Integer.parseInt(map.get("limit"));
        PageHelper.startPage(page, limit);

        CouponExample couponExample = new CouponExample();
        //OrderByClause对应排序方式
        couponExample.setOrderByClause(map.get("sort") + " " + map.get("order"));
        //搜索条件（与）
        //判断有没有请求此key
        CouponExample.Criteria criteria = couponExample.createCriteria();
        if (map.containsKey("name")) {
            criteria.andNameLike("%" + map.get("name") + "%");
        }
        if (map.containsKey("status")) {
            criteria.andStatusEqualTo(Short.valueOf(map.get("status")));
        }
        if (map.containsKey("type")) {
            criteria.andTypeEqualTo(Short.valueOf(map.get("type")));
        }
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);

        PageInfo<Coupon> couponPageInfo = new PageInfo<>(couponList);
        long total = couponPageInfo.getTotal();
        DataVo<Coupon> couponDataVo = new DataVo<>();
        couponDataVo.setTotal(total);
        couponDataVo.setItems(couponList);
        return couponDataVo;
    }

    //优惠券添加
    @Override
    public Coupon couponCreate(Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date addTime = null;
        try {
            addTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        coupon.setAddTime(addTime);

        String s = UUID.randomUUID().toString();
        coupon.setCode(s.substring(0, s.indexOf("-")).toUpperCase());
        int insert = couponMapper.insert(coupon);
        int id = couponMapper.selectLastInsertId();
        //查询数据库得到响应Date
        Coupon coupon1 = couponMapper.selectByPrimaryKey(id);
        return coupon1;
    }

    //优惠券详情
    @Override
    public Coupon couponRead(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }

    //优惠券编辑
    @Override
    public Coupon couponUpdate(Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date updateTime = null;
        try {
            updateTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        coupon.setUpdateTime(updateTime);

        int update = couponMapper.updateByPrimaryKey(coupon);
        Coupon coupon1 = couponMapper.selectByPrimaryKey(coupon.getId());
        return coupon1;
    }

    //优惠券删除
    @Override
    public Integer couponDelete(Coupon coupon) {
        int delete = couponMapper.deleteByPrimaryKey(coupon.getId());
        return delete;
    }

    @Override
    public List<Coupon> couponAllList() throws DbException {
        try {
            PageHelper.startPage(1,5);
            return couponMapper.selectByExample((CouponExample) null);
        } catch (Exception e) {
            throw new DbException("服务器异常");
        }
    }
    //-----------------------------------------------------------------------------------------------

    @Override
    public Map<String, Object> wxCouponList(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer size = Integer.parseInt(map.get("size"));
        PageHelper.startPage(page, size);

        CouponExample couponExample = new CouponExample();
        List<Coupon> wxCouponList = couponMapper.selectByExample(couponExample);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("data",wxCouponList);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(wxCouponList);
        long total = couponPageInfo.getTotal();
        map1.put("count",total);
        return map1;
    }
}
