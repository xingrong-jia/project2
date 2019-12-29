package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Collect;
import com.jiaxingrong.model.CollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface CollectMapper {
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    Integer selectNumByValueId(@Param("valueId") Integer valueId);

    List<Collect> selectByType(@Param("type") Integer type, @Param("userId") Integer userId);

    Collect selectByValueId(@Param("valueId") Integer valueId, @Param("userId") Integer userId);

    void deleteByValueId(@Param("valueId") Integer valueId, @Param("userId") Integer userId);
}
