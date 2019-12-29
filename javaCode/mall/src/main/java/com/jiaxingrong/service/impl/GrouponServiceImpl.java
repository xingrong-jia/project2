package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.bean.example.GrouponRulesExample;
import com.jiaxingrong.bean.example.OrderGoodsExample;
import com.jiaxingrong.bean.type.GrouponRules;
import com.jiaxingrong.bean.type.OrderGoods;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.mapper.*;
import com.jiaxingrong.model.*;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.requstov.wx.GrouponBean;
import com.jiaxingrong.requstov.wx.HandleOptionBean;
import com.jiaxingrong.service.inter.GrouponService;
import com.jiaxingrong.tools.HandleOptionTool;
import com.jiaxingrong.tools.StringTools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:47
 * @Version 1.0
 */

@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;


    @Override
    public DataVo grouponListRecord(Map<String, String> map) throws AdEx {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer limit = Integer.parseInt(map.get("limit"));
        PageHelper.startPage(page, limit);


        GrouponExample grouponExample = new GrouponExample();
        GrouponExample.Criteria criteria1 = grouponExample.createCriteria();
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();

        List<Map> list = new ArrayList<>();
        if (map.containsKey("goodsId") && map.get("goodsId") != "") {
            if (!StringTools.isNum(map.get("goodsId"))) {
                throw new AdEx("输入类型不匹配");
            }
            criteria.andGoodsIdEqualTo(Integer.valueOf(map.get("goodsId")));
            List<GrouponRules> grouponRulesList = grouponRulesMapper.selectByExample(grouponRulesExample);
            for (GrouponRules grouponRules : grouponRulesList) {
                criteria1.andRulesIdEqualTo(grouponRules.getId());
                List<Groupon> grouponList = grouponMapper.selectByExample(grouponExample);
                for (Groupon groupon : grouponList) {
                    Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
                    Map map1 = new HashMap();
                    map1.put("groupon", groupon);
                    map1.put("goods", goods);
                    map1.put("rules", grouponRules);
                    ArrayList<Groupon> subGroupons = new ArrayList<>();
                    for (Groupon groupon1 : grouponList) {
                        if (groupon1.getGrouponId() == groupon.getGrouponId()) {
                            subGroupons.add(groupon1);
                        }
                    }
                    subGroupons.add(groupon);
                    map1.put("subGroupons", subGroupons);
                    list.add(map1);
                }
            }
        } else {
            List<Groupon> grouponList = grouponMapper.selectByExample(grouponExample);
            for (Groupon groupon : grouponList) {
                criteria.andIdEqualTo(groupon.getRulesId());
                //只能查一条
                List<GrouponRules> grouponRulesList = grouponRulesMapper.selectByExample(grouponRulesExample);
                Goods goods = goodsMapper.selectByPrimaryKey(grouponRulesList.get(0).getGoodsId());
                Map map1 = new HashMap();
                map1.put("groupon", groupon);
                map1.put("goods", goods);
                map1.put("rules", grouponRulesList.get(0));
                ArrayList<Groupon> subGroupons = new ArrayList<>();
                //再遍历一次
                for (Groupon groupon1 : grouponList) {
                    if (groupon1.getGrouponId() == groupon.getGrouponId()) {
                        subGroupons.add(groupon1);
                    }
                }
                map1.put("subGroupons", subGroupons);
                list.add(map1);
            }
        }

        PageInfo<Map> grouponPageInfo = new PageInfo<>(list);
        long groupon = grouponPageInfo.getTotal();

        DataVo<Map> grouponDataVo = new DataVo<>();
        grouponDataVo.setTotal(groupon);
        grouponDataVo.setItems(list);
        return grouponDataVo;
    }

    //---------------------------------------------------------------------------------------
    @Override
    public List<GrouponBean> getGrouponList() throws DbException {
        try {
            return grouponMapper.selectGrouponDetails();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("服务器异常");
        }
    }

    @Override
    public Map<String, Object> wxGrouponDetail(Integer grouponId) {
        //团购订单
        Groupon groupon = grouponMapper.selectByPrimaryKey(grouponId);
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andGrouponIdEqualTo(groupon.getGrouponId());
        //当前团购-多人
        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);
        List<User> users = new ArrayList<>();
        for (Groupon groupon1 : groupons) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(groupon1.getUserId());
            List<User> users1 = userMapper.selectByExample(userExample);
            users.add(users1.get(0));
        }
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getGrouponId());
        Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId());
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        List<Goods> goodsList = null;
        for (OrderGoods orderGoods1 : orderGoods) {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIdEqualTo(orderGoods1.getGoodsId());
            goodsList = goodsMapper.selectByExample(goodsExample);
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(groupon.getCreatorUserId());
        List<User> createUsers = userMapper.selectByExample(userExample);
        Map<String, Object> map = new HashMap<>();
        map.put("creator", createUsers);
        map.put("groupon", groupon);
        map.put("joiners", users);
        map.put("orderInfo", order);
        map.put("orderGoods", goodsList);
        map.put("rules", grouponRules);
        map.put("linkGrouponId", groupon.getId());
        return map;
    }

    @Override
    public Map<String, Object> wxGrouponMy(Integer showType) {
        GrouponExample grouponExample = new GrouponExample();
        User user = user1();
        grouponExample.createCriteria().andUserIdEqualTo(user.getId());
        //该用户的团购
        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);
        ArrayList<Object> showType0= new ArrayList<>();
        ArrayList<Object> showType1 = new ArrayList<>();
        int showTypeCount0 = 0;
        int showTypeCount1 = 0;
        for (Groupon groupon : groupons) {
            Map map = new HashMap();
            Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());

            setOrderStatusText(order,map);//map.put("orderStatusText","未付款");

            User createUser = userMapper.selectByPrimaryKey(groupon.getCreatorUserId());
            map.put("creator", createUser.getNickname());
            map.put("groupon", groupon);
            map.put("orderId", order.getId());
            map.put("orderSn", order.getOrderSn());
            map.put("actualPrice", order.getActualPrice());
            GrouponExample grouponExample1 = new GrouponExample();
            grouponExample1.createCriteria().andGrouponIdEqualTo(groupon.getGrouponId());
            List<Groupon> groupons1 = grouponMapper.selectByExample(grouponExample1);
            map.put("joinerCount",groupons1.size());
            OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
            orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId());
            List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
            List<Goods> goodsList = null;
            for (OrderGoods orderGoods1 : orderGoods) {
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andIdEqualTo(orderGoods1.getGoodsId());
                goodsList = goodsMapper.selectByExample(goodsExample);
            }
            map.put("goodsList",goodsList);
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getGrouponId());
            map.put("rules",grouponRules);
            map.put("id",groupon.getId());
            map.put("isCreator",true);
            HandleOptionBean orderHandleOptionBean = HandleOptionTool.getOrderHandleOptionBean(order);
            map.put("handleOption",orderHandleOptionBean);

            if(user.getId().equals(groupon.getCreatorUserId())) {
                showType0.add(map);
                showTypeCount0++;
            }else{
                showType1.add(map);
                showTypeCount1++;
            }
        }
        Map<String,Object> map1 = new HashMap<>();
        if(showType == 0){
            map1.put("data",showType0);
            map1.put("count",showTypeCount0);
            return map1;
        }
        map1.put("data",showType1);
        map1.put("count",showTypeCount1);
        return map1;
    }
    //--------------------------------------------------------------------------------------------------
    public static User user1(){
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user;
    }

    public void setOrderStatusText(Order order,Map<String,Object> map){
        switch (order.getOrderStatus()){
            case (short)101:
                map.put("orderStatusText","未付款");
                break;
            case (short)102:
                map.put("orderStatusText","用户取消");
                break;
            case (short)103:
                map.put("orderStatusText","系统取消");
                break;
            case (short)201:
                map.put("orderStatusText","已付款");
                break;
            case (short)202:
                map.put("orderStatusText","申请退款");
                break;
            case (short)203:
                map.put("orderStatusText","已退款");
                break;
            case (short)301:
                map.put("orderStatusText","已发货");
                break;
            case (short)401:
                map.put("orderStatusText","用户收货");
                break;
            case (short)402:
                map.put("orderStatusText","系统收货");
                break;
        }
    }
}
