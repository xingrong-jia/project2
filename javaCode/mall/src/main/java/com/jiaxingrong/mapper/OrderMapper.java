package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Order;
import com.jiaxingrong.model.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectOrderListByUserIdAndOrderAndOrOrderStatusArray(@Param("userId") Integer userId, @Param("orderSn") String orderSn, @Param("orderStatusArray") Short[] orderStatusArray);
}