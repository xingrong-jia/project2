package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.OrderMapper;
import com.jiaxingrong.mapper.Order_goodsMapper;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    Order_goodsMapper order_goodsMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 显示所有订单信息
     *
     * @return
     */
    @Override
    public Map orderList(Laypage laypage) {
        Map map = new HashMap();
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andDeletedEqualTo(false);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", orderList);
        return map;
    }

    /**
     * 查询出订单详情信息
     * 1.订单详情
     * 2.订单商品详情
     * 3.用户部分信息
     *
     * @param id
     * @return
     */
    @Override
    public Map orderDetail(Integer id) {
        /*OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(id);
        List<Order> orderList = orderMapper.selectByExample(orderExample);*/
        Map<String, Object> map = new HashMap<>();

        Order order = orderMapper.selectByPrimaryKey(id);
        Order_goodsExample order_goodsExample = new Order_goodsExample();
        order_goodsExample.createCriteria().andOrderIdEqualTo(order.getId());
        List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        Map userMap = new HashMap();
        userMap.put("nickname", user.getNickname());
        userMap.put("avatar", user.getAvatar());

        map.put("orderGoods", order_goods);
        map.put("user", userMap);
        map.put("order", order);

        return map;
    }

    /**
     * 根据用户ID和订单编号和订单状态查询出符合条件的订单
     *
     * @param laypage
     * @return 用户id 和订单编号是精准匹配
     * 订单状态是里面的是or的关系
     */
    @Override
    public Map queryOrderListByUserIdAndOrderAndOrOrderStatusArray(Laypage laypage) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        List<Order> orderList = orderMapper.selectOrderListByUserIdAndOrderAndOrOrderStatusArray(laypage.getUserId(),
                laypage.getOrderSn(),laypage.getOrderStatusArray());
       /* OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserIdEqualTo(laypage.getUserId()).
        andOrderSnEqualTo(laypage.getOrderSn()).andOrderStatusBetween(laypage.getOrderStatusArray());
        List<Order> orderList = orderMapper.selectByExample(orderExample);*/
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", orderList);
        return map;
    }


}
