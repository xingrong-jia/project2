package com.jiaxingrong.mapper;

import com.jiaxingrong.model.User;
import com.jiaxingrong.model.UserExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Date> selectData();

    Integer selectNum(@Param("date") Date date);

    User selectNickNameAndAvatar(@Param("userId") Integer userId);

    User selectByUsername(@Param("username") String username);


    Integer getUserOrderUnpaid(@Param("id") int id);

    Integer getUserOrderUncomment(@Param("id") int id);

    Integer getUserOrderUnrecv(@Param("id") int id);

    Integer getUserOrderUnship(@Param("id") int id);
}
