package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.model.Ad;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.List;
import java.util.Map;

/**
 * 推广管理
 * @Author:luchang
 * @Date: 2019/12/26 20:31
 * @Version 1.0
 */
public interface AdService {
    //广告管理-页面显示及搜索
    DataVo adlist(Map<String,String> map);
    //添加广告
    Ad adCreate(Ad ad) throws AdEx;
    //编辑广告
    Ad adUpdate(Ad ad) throws AdEx;
    //删除广告
    Integer adDelete(Ad ad) throws AdEx;

    List<Ad> getAllList() throws DbException;
}