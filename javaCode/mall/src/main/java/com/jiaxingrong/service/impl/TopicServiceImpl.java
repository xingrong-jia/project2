package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.bean.example.TopicExample;
import com.jiaxingrong.bean.type.Topic;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.mapper.TopicMapper;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 14:55
 * @Version 1.0
 */

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;

    //显示
    @Override
    public DataVo topicList(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer limit = Integer.parseInt(map.get("limit"));
        PageHelper.startPage(page, limit);

        TopicExample topicExample = new TopicExample();
        //OrderByClause对应排序方式
        topicExample.setOrderByClause(map.get("sort") + " " + map.get("order"));
        TopicExample.Criteria criteria = topicExample.createCriteria();

        if (map.containsKey("title")) {
            criteria.andTitleLike("%" + map.get("title") + "%");
        }
        if (map.containsKey("subtitle")) {
            criteria.andSubtitleLike("%" + map.get("subtitle") + "%");
        }

        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);

        PageInfo<Topic> topicPageInfo = new PageInfo<>(topicList);
        long total = topicPageInfo.getTotal();

        DataVo<Topic> topicDataVo = new DataVo<>();
        topicDataVo.setTotal(total);
        topicDataVo.setItems(topicList);
        return topicDataVo;
    }

    //添加
    @Override
    public Topic topicCreate(Topic topic) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date addTime = null;
        try {
            addTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        topic.setAddTime(addTime);

        int insert = topicMapper.insert(topic);
        int id = topicMapper.selectLastInsertId();
        //查询数据库得到响应Date
        Topic topic1 = topicMapper.selectByPrimaryKey(id);
        return topic1;
    }
    //编辑

    @Override
    public Topic topicUpdate(Topic topic) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date updateTime = null;
        try {
            updateTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        topic.setUpdateTime(updateTime);

        int update = topicMapper.updateByPrimaryKey(topic);
        Topic topic1 = topicMapper.selectByPrimaryKey(topic.getId());
        return topic1;
    }

    //删除
    @Override
    public Integer topicDelete(Topic topic) {
        int topicDelete = topicMapper.deleteByPrimaryKey(topic.getId());
        return topicDelete;
    }
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public Map<String, Object> wxTopicList(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer size = Integer.parseInt(map.get("size"));
        PageHelper.startPage(page, size);

        TopicExample topicExample = new TopicExample();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        //封装
        Map<String, Object> map1 = new HashMap<>();
        map1.put("data", topics);

        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        map1.put("count", total);

        return map1;
    }

    @Override
    public Map<String, Object> wxTopicDetail(Integer id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("topic", topic);
        map1.put("goods", topic.getGoods());
        return map1;
    }

    @Override
    public List<Topic> wxTopicRelated(Integer id) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andIdNotEqualTo(id);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public List<Topic> getAllList() throws DbException {
        try {
            PageHelper.startPage(1, 6);
            return topicMapper.selectByExample(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("服务器异常");
        }
    }
}
