package com.jiaxingrong.mapper;

import com.jiaxingrong.bean.example.OrderGoodsExample;
import com.jiaxingrong.bean.type.OrderGoods;
import com.jiaxingrong.requstov.wx.GoodsBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:50
 * @Version 1.0
 */

public interface OrderGoodsMapper {
    long countByExample(OrderGoodsExample example);

    int deleteByExample(OrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsExample example);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<Date> selectData();

    Double selectAmount(@Param("date") Date date);

    Integer selectProducts(@Param("date") Date date);

    Integer selectOrders(@Param("date") Date date);

    List<OrderGoods> selectOrderGoodsInfoById(Integer orderId);

    GoodsBean selectOrderGoodsMain(@Param("orderId") Integer orderId, @Param("goodsId") Integer goodsId);

    int updateComment(@Param("orderGoodsId") int orderGoodsId);
}
