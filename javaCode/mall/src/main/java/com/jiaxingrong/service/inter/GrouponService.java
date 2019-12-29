package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.requstov.wx.GrouponBean;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:46
 * @Version 1.0
 */
public interface GrouponService {
    DataVo grouponListRecord(Map<String,String> map) throws AdEx;

    List<GrouponBean> getGrouponList() throws DbException;
    //---------------------------------------------------------------------------------------

    Map<String, Object> wxGrouponDetail(Integer grouponId);

    Map<String, Object> wxGrouponMy(Integer showType);
}

