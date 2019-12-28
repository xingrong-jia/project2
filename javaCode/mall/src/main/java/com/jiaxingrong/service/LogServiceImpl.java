package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.jiaxingrong.mapper.LogMapper;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Log;
import com.jiaxingrong.model.LogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements  LogService {
    @Autowired
    LogMapper logMapper;
    @Override
    public List<Log> getLogListByPage(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        LogExample example = new LogExample();
        example.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        LogExample.Criteria criteria = example.createCriteria();
        if (laypage.getName() != null) {
            criteria.andAdminLike("%" + laypage.getName() + "%");
        }
        List<Log> logList = logMapper.selectByExample(example);
        return logList;
    }
}
