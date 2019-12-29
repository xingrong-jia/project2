package com.jiaxingrong.service.inter;

import com.jiaxingrong.bean.type.Topic;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 14:55
 * @Version 1.0
 */
public interface TopicService {
    DataVo topicList(Map<String,String> map);

    Topic topicCreate(Topic topic);

    Topic topicUpdate(Topic topic);

    Integer topicDelete(Topic topic);

    List<Topic> getAllList() throws DbException;
    //------------------------------------------------------------------------------------
    Map<String, Object> wxTopicList(Map<String, String> map);

    Map<String, Object> wxTopicDetail(Integer id);

    List<Topic> wxTopicRelated(Integer id);
}
