package com.jiaxingrong.mapper;

import com.jiaxingrong.bean.example.TopicExample;
import com.jiaxingrong.bean.type.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:07
 * @Version 1.0
 */
public interface TopicMapper {
    long countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExampleWithBLOBs(TopicExample example);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer id);
    //
    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExampleWithBLOBs(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);
}
