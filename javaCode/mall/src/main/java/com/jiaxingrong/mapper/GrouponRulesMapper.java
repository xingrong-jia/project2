package com.jiaxingrong.mapper;

import com.jiaxingrong.bean.example.GrouponRulesExample;
import com.jiaxingrong.bean.type.GrouponRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:luchang
 * @Version 1.0
 */
public interface GrouponRulesMapper {
    long countByExample(GrouponRulesExample example);

    int deleteByExample(GrouponRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    List<GrouponRules> selectByExample(GrouponRulesExample example);

    GrouponRules selectByPrimaryKey(Integer id);

    //
    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByExample(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> selectByGoodsId(@Param("goodsId") Integer goodsId);

    int selecJoinerConut(Integer id);
}
