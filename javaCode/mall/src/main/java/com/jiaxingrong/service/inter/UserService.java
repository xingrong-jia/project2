package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.execption.InputException;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.requstov.wx.UserIndexVo;

import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:06
 * @Version 1.0
 */
public interface UserService {
    /**
     * 获取用户总数
     *
     * @return
     */
    long getTotals();

    DataVo userList (Map<String, Object> map) throws InputException;

    Map<String, UserIndexVo> getUserOrderInfo(int id) throws DbException;

    int register(Map<String, String> map) throws DbException;

    int resetPassword(Map<String, String> map) throws DbException;
}
