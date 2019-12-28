package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface OrderService {
    Map orderList(Laypage laypage);

    Map orderDetail(Integer id);

    Map queryOrderListByUserIdAndOrderAndOrOrderStatusArray(Laypage laypage);
}
