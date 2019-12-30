package com.jiaxingrong.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.service.UserService;
import com.jiaxingrong.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
/**
 * 用户模块的实现
 */
public class UserController {

    @Autowired
    UserServiceImpl userServiceimpl;// 从spring容器中获取这个组件

    /**
     * 用户管理模块会员管理功能首页显示和查询User
     */
    @RequestMapping("user/list")
    public BaseReqVo listuser(int page,int limit,String username,String mobile,String sort,String order) {
        //使用List来接收
        List<User> userList = userServiceimpl.listuser(page,limit,username,mobile,sort,order);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        long total = userPageInfo.getTotal();
        Map map = new HashMap();
        map.put("total",total);
        map.put("items",userList);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 用户管理模块收货地址功能首页显示和查询具体user地址
     */
    @RequestMapping("address/list")
    public BaseReqVo listaddress(int page,int limit,Integer userId,String name,String sort,String order) {
        List<Address> listaddress = userServiceimpl.listaddress(page,limit,userId,name,sort,order);
        PageInfo<Address> pageInfo = new PageInfo<>(listaddress);
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("total",total);
        map.put("items",listaddress);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    /**
     * 用户管理模块会员收藏功能首页显示和查询具体User收藏商品接口
     */
    @RequestMapping("collect/list")
    public BaseReqVo listcollect(int page,int limit,Integer userId,Integer valueId,String sort,String order) {
        List<Collect> listcollect = userServiceimpl.listcollect(page,limit,userId,valueId,sort,order);
        PageInfo<Collect> pageInfo = new PageInfo<>(listcollect);
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("total",total);
        map.put("items",listcollect);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    /**
     * 用户管理模块会员足迹功能首页显示及具体User浏览信息查询接口
     */
    @RequestMapping("footprint/list")
    public BaseReqVo listfootprint(int page,int limit,Integer userId,Integer goodsId,String sort,String order) {
        List<Footprint> listfootprint = userServiceimpl.listfootprint(page, limit, userId, goodsId, sort, order);
        PageInfo<Footprint> pageInfo = new PageInfo<>(listfootprint);
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("total", total);
        map.put("items", listfootprint);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    /**
     * 用户管理模块搜索历史功能首页显示及具体User浏览信息查询接口
     */
    @RequestMapping("history/list")
    public BaseReqVo listhistory(int page,int limit,Integer userId,String keyword,String sort,String order) {
        List<Search_history> listhistory = userServiceimpl.listhistory(page,limit,userId,keyword,sort,order);
        PageInfo<Search_history> pageInfo = new PageInfo<>(listhistory);
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("total",total);
        map.put("items",listhistory);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    /**
     * 用户管理模块意见反馈功能首页显示及具体User反馈信息查询接口
     */
    @RequestMapping("feedback/list")
    public BaseReqVo listfeedback(int page,int limit,Integer id,String username,String sort,String order) {
        List<Feedback> listfeedback = userServiceimpl.listfeedback(page,limit,id,username,sort,order);
        PageInfo<Feedback> pageInfo = new PageInfo<>(listfeedback);
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("total",total);
        map.put("items",listfeedback);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
