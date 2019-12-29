package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.bean.example.GrouponRulesExample;
import com.jiaxingrong.bean.type.GrouponRules;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.mapper.GoodsMapper;
import com.jiaxingrong.mapper.GrouponRulesMapper;
import com.jiaxingrong.model.Goods;
import com.jiaxingrong.model.GoodsExample;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.GrouponRulesService;
import com.jiaxingrong.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:42
 * @Version 1.0
 */

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    //显示和搜索规则
    @Override
    public DataVo grouponRulesList(Map<String, String> map) throws AdEx {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer limit = Integer.parseInt(map.get("limit"));
        PageHelper.startPage(page, limit);

        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        //OrderByClause对应排序方式
        grouponRulesExample.setOrderByClause(map.get("sort") + " " + map.get("order"));
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();

        if (map.containsKey("goodsId") && map.get("goodsId") != "") {
            /*try{错误
                criteria.andGoodsIdEqualTo((Integer.valueOf(map.get("goodsId"))));
            }catch (Exception e){
                throw new AdEx("输入类型不匹配");
            }*/
            if (!StringTools.isNum(map.get("goodsId"))) {
                throw new AdEx("输入类型不匹配");
            }
            criteria.andGoodsIdEqualTo((Integer.valueOf(map.get("goodsId"))));
        }

        List<GrouponRules> grouponRulesList = grouponRulesMapper.selectByExample(grouponRulesExample);

        PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponRulesList);
        long total = grouponRulesPageInfo.getTotal();

        DataVo<GrouponRules> grouponRulesDataVo = new DataVo<>();
        grouponRulesDataVo.setTotal(total);
        grouponRulesDataVo.setItems(grouponRulesList);
        return grouponRulesDataVo;
    }
    //添加
    @Override
    public GrouponRules grouponRulesCreate(GrouponRules grouponRules) throws AdEx {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date addTime = null;
        try {
            addTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        grouponRules.setAddTime(addTime);

        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        if(goods == null){
            throw new AdEx("没找到对应商品QAQ");
        }
        if(goods.getRetailPrice().intValue() <= grouponRules.getDiscount().intValue()){
            throw new AdEx("住手，折扣已经超过商品价格了QAQ");
        }
        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());

        int insert = grouponRulesMapper.insert(grouponRules);
        int id = grouponRulesMapper.selectLastInsertId();
        //查询数据库得到响应Date
        GrouponRules grouponRules1 = grouponRulesMapper.selectByPrimaryKey(id);
        return grouponRules1;
    }
    //编辑
    @Override
    public GrouponRules grouponRulesUpdate(GrouponRules grouponRules) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date updateTime = null;
        try {
            updateTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        grouponRules.setUpdateTime(updateTime);

        int update = grouponRulesMapper.updateByPrimaryKey(grouponRules);
        GrouponRules grouponRules1 = grouponRulesMapper.selectByPrimaryKey(grouponRules.getId());
        return  grouponRules1;
    }
    //删除
    @Override
    public Integer grouponRulesDelete(GrouponRules grouponRules) {
        int grouponRulesDelete = grouponRulesMapper.deleteByPrimaryKey(grouponRules.getId());
        return grouponRulesDelete;
    }
    //---------------------------------------------------------------------------------------


    @Override
    public Map<String, Object> wxGrouponList(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer size = Integer.parseInt(map.get("size"));
        PageHelper.startPage(page, size);

        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        List<GrouponRules> grouponRulesList = grouponRulesMapper.selectByExample(grouponRulesExample);
        List dataList = new ArrayList<>();
        for (GrouponRules grouponRules:grouponRulesList) {
            GoodsExample goodsExample = new GoodsExample();
            Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
            Map<String,Object> map1 = new HashMap<>();
            double groupon_price = goods.getRetailPrice().doubleValue() - grouponRules.getDiscount().doubleValue();
            if(groupon_price < 0){
                groupon_price = 0;
            }
            map1.put("groupon_price",groupon_price);
            map1.put("goods",goods);
            map1.put("groupon_member",grouponRules.getDiscountMember());
            dataList.add(map1);
        }
        Map<String,Object> map2 = new HashMap<>();
        map2.put("data",dataList);
        PageInfo<GrouponRules> topicPageInfo = new PageInfo<>(grouponRulesList);
        long total = topicPageInfo.getTotal();
        map2.put("count",total);
        return map2;
    }
}
