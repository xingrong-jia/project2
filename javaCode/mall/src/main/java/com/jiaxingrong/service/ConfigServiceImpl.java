package com.jiaxingrong.service;

import com.jiaxingrong.mapper.SystemMapper;
import com.jiaxingrong.model.ExpressRequestBody;
import com.jiaxingrong.model.System;
import com.jiaxingrong.model.SystemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigServiceImpl implements ConfigService {

    //
    @Autowired
    SystemMapper systemMapper;
    @Override
    public Map listmall() {
        SystemExample systemExample = new SystemExample();
        List<Integer> idlist = new ArrayList<>();
        idlist.add(12);
        idlist.add(14);
        idlist.add(6);
        idlist.add(8);
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andIdIn(idlist);
        List<System> systems = systemMapper.selectByExample(systemExample);
        Map map = new HashMap();
        String[] malldata = {"cskaoyan_mall_mall_name","cskaoyan_mall_mall_qq","cskaoyan_mall_mall_phone","cskaoyan_mall_mall_address"};
        int i = 0;
        for (System system : systems) {
            map.put(malldata[i++],system.getKeyValue());
        }
        return map;
    }

    public Integer publicUpdate(String keyName, String keyValue) {
        SystemExample example = new SystemExample();
        SystemExample.Criteria criteria = example.createCriteria();
        criteria.andKeyNameEqualTo(keyName);
        System system = new System();
        system.setKeyValue(keyValue);
        int update = systemMapper.updateByExampleSelective(system, example);
        return update;
    }
    @Override
    public Integer updateMall(ExpressRequestBody requestBody) {
        // 观察Mapper的updateByExampleSelective方法，发现需要把requestBody的数据set进system对象里
        int update = publicUpdate("cskaoyan_mall_mall_name", requestBody.getCskaoyan_mall_mall_name());
        publicUpdate("cskaoyan_mall_mall_phone", requestBody.getCskaoyan_mall_mall_phone());
        publicUpdate("cskaoyan_mall_mall_address", requestBody.getCskaoyan_mall_mall_address());
        publicUpdate("cskaoyan_mall_mall_qq", requestBody.getCskaoyan_mall_mall_qq());

        return update;
    }

    @Override
    public Map listOrder() {
        SystemExample example = new SystemExample();
        SystemExample.Criteria criteria = example.createCriteria();
        List<String> keyNameList = new ArrayList<>();
        keyNameList.add("cskaoyan_mall_order_unconfirm");
        keyNameList.add("cskaoyan_mall_order_unpaid");
        keyNameList.add("cskaoyan_mall_order_comment");


        criteria.andKeyNameIn(keyNameList);
        List<System> systems = systemMapper.selectByExample(example);
        Map map = new LinkedHashMap();
        String[] arr = {"cskaoyan_mall_order_unpaid","cskaoyan_mall_order_unconfirm","cskaoyan_mall_order_comment"};
        int i = 0;
        for (System system : systems) {
            map.put(arr[i++],system.getKeyValue());
        }
        return map;

    }

    @Override
    public Integer updateOrder(ExpressRequestBody requestBody) {
        int update = publicUpdate("cskaoyan_mall_order_unconfirm", requestBody.getCskaoyan_mall_order_unconfirm());
        publicUpdate("cskaoyan_mall_order_unpaid", requestBody.getCskaoyan_mall_order_unpaid());
        publicUpdate("cskaoyan_mall_order_comment", requestBody.getCskaoyan_mall_order_comment());
        return update;
    }

    @Override
    public Map listWx() {
        SystemExample example = new SystemExample();
        SystemExample.Criteria criteria = example.createCriteria();
        List<String> keyNameList = new ArrayList<>();
        keyNameList.add("cskaoyan_mall_wx_index_new");
        keyNameList.add("cskaoyan_mall_wx_share");
        keyNameList.add("cskaoyan_mall_wx_index_hot");
        keyNameList.add("cskaoyan_mall_wx_catlog_goods");
        keyNameList.add("cskaoyan_mall_wx_catlog_list");
        keyNameList.add("cskaoyan_mall_wx_index_brand");
        keyNameList.add("cskaoyan_mall_wx_index_topic");
        criteria.andKeyNameIn(keyNameList);
        List<System> systems = systemMapper.selectByExample(example);
        Map map = new HashMap();
        String[] arr = {"cskaoyan_mall_wx_index_new","cskaoyan_mall_wx_share",
                "cskaoyan_mall_wx_index_hot","cskaoyan_mall_wx_catlog_goods",
                "cskaoyan_mall_wx_catlog_list","cskaoyan_mall_wx_index_brand",
                "cskaoyan_mall_wx_index_topic"};
        int i = 0;
        for (System system : systems) {
            map.put(arr[i++],system.getKeyValue());
        }
        return map;
    }

    @Override
    public Integer updateWx(ExpressRequestBody requestBody) {
        int update = publicUpdate("cskaoyan_mall_wx_index_new", requestBody.getCskaoyan_mall_wx_index_new());
        publicUpdate("cskaoyan_mall_wx_index_hot", requestBody.getCskaoyan_mall_wx_index_hot());
        publicUpdate("cskaoyan_mall_wx_index_brand", requestBody.getCskaoyan_mall_wx_index_brand());
        publicUpdate("cskaoyan_mall_wx_index_topic", requestBody.getCskaoyan_mall_wx_index_topic());
        publicUpdate("cskaoyan_mall_wx_catlog_goods", requestBody.getCskaoyan_mall_wx_catlog_goods());
        publicUpdate("cskaoyan_mall_wx_catlog_list", requestBody.getCskaoyan_mall_wx_catlog_list());
        publicUpdate("cskaoyan_mall_wx_share", requestBody.getCskaoyan_mall_wx_share());
        return update;
    }


    @Override
    public Map listexpress() {
        SystemExample example = new SystemExample();
        SystemExample.Criteria criteria = example.createCriteria();
        List<String> keyNameList = new ArrayList<>();
        keyNameList.add("cskaoyan_mall_wx_index_new");
        criteria.andKeyNameIn(keyNameList);
        List<System> systems = systemMapper.selectByExample(example);
        Map map = new HashMap();
        String[] arr = {"cskaoyan_mall_express_freight_min", "cskaoyan_mall_express_freight_value"};
        int i = 0;
        for (System system : systems) {
            map.put(arr[i++],system.getKeyValue());
        }
        return map;
    }

    @Override
    public Integer updateExpress(ExpressRequestBody requestBody) {
        int update = publicUpdate("cskaoyan_mall_express_freight_min", requestBody.getCskaoyan_mall_express_freight_min());
        publicUpdate("cskaoyan_mall_express_freight_value", requestBody.getCskaoyan_mall_express_freight_value());
        return update;
    }



}
