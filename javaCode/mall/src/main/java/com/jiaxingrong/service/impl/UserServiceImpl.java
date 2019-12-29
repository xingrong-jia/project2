package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.execption.InputException;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.User;
import com.jiaxingrong.model.UserExample;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.requstov.wx.UserIndexVo;
import com.jiaxingrong.service.inter.UserService;
import com.jiaxingrong.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:59
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public long getTotals() {
        return userMapper.countByExample(null);
    }

    /**
     * 查询所有用户信息
     *
     * @param map map中封装着前端传来的page、limit、sort、order信息
     * @return 返回DataVo类型数据，其中封装着List<User>和total
     */
    @Override
    public DataVo userList(Map<String, Object> map) throws InputException {
        // 取出页码
        Integer page = Integer.parseInt((String) map.get("page"));
        // 取出每页数据
        Integer limit = Integer.parseInt((String) map.get("limit"));
        // 实现分页操作
        PageHelper.startPage(page, limit);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        // 实现以指定规则排序
        userExample.setOrderByClause(map.get("sort") + " " + map.get("order"));
        // 取出用户名
        String username = (String) map.get("username");
        // 根据用户名进行模糊查询
        if (!StringTools.isNull(username)) {
            if (username.length() > 20) {
                throw new InputException("用户名输入过长，参数长度保证小于20");
            }
            criteria.andUsernameLike("%" + username + "%");
        }
        // 取出手机号
        String mobile = (String) map.get("mobile");
        // 根据手机号进行模糊查询
        if (!StringTools.isNull(mobile)) {
            if (mobile.length() > 11) {
                throw new InputException("手机号输入过长，参数长度保证小于11");
            }
            if (!StringTools.isNum(mobile)) {
                throw new InputException("请保证输入的手机号为数字");
            }
            criteria.andMobileLike("%" + mobile + "%");
        }
        List<User> users = userMapper.selectByExample(userExample);
        // 通过pageInfo获取符合查询条件的总条目
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        DataVo listDataVo = new DataVo<>();
        listDataVo.setItems(users);
        listDataVo.setTotal(total);
        return listDataVo;
    }

    @Override
    public Map<String, UserIndexVo> getUserOrderInfo(int id) throws DbException {
        try {
            Integer unpaid = userMapper.getUserOrderUnpaid(id);
            Integer userOrderUncomment = userMapper.getUserOrderUncomment(id);
            Integer userOrderUnrecv = userMapper.getUserOrderUnrecv(id);
            Integer userOrderUnship = userMapper.getUserOrderUnship(id);
            UserIndexVo userIndexVo = new UserIndexVo(userOrderUncomment, userOrderUnrecv, unpaid, userOrderUnship);
            HashMap<String, UserIndexVo> map = new HashMap<>(1);
            map.put("order", userIndexVo);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("服务器异常，获取用户订单信息失败");
        }

    }

    @Override
    public int register(Map<String, String> map) throws DbException {
        String username = map.get("username");
        String password = map.get("password");
        if (!StringTools.isNull(username) && !StringTools.isNull(password)) {
            try {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                Date addTime = new Date();
                user.setAddTime(addTime);
                user.setUpdateTime(addTime);
                user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(15);
                user.setNickname("wx_" + uuid);
                user.setMobile(map.get("mobile"));
                int re = userMapper.insertSelective(user);
                return re;
            } catch (Exception e) {
                throw new DbException("手机号已被注册");
            }
        }
        return 0;
    }

    @Override
    public int resetPassword(Map<String, String> map) throws DbException {
        String mobile = map.get("mobile");
        User user = new User();
        String password = map.get("password");
        if (!StringTools.isNull(password)) {
            user.setPassword(password);
            user.setUpdateTime(new Date());
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        try {
            return userMapper.updateByExampleSelective(user, userExample);
        } catch (Exception e) {
            throw new DbException("服务器异常,修改失败");
        }
    }
}
