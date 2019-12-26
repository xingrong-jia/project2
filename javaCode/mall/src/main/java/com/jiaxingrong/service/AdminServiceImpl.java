package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.mapper.LogMapper;
import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.mapper.RoleMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    LogMapper logMapper;


    @Override
    public List<Log> getAdminInformationByPage(AdminReqVo adminReqVo) {
        // 取出起始页数和每页显示条目数
        Integer pageNumber = adminReqVo.getPage();
        Integer size = adminReqVo.getLimit();
        // 开启分页
        PageHelper.startPage(pageNumber, size);
        LogExample example = new LogExample();
        // 取出sort
        String sort = adminReqVo.getSort();
        // 开始设置限制条件
        example.setOrderByClause(sort);
        // 用example弄出它的内部类
        LogExample.Criteria criteria = example.createCriteria();
        // 取出username
        String username = adminReqVo.getUsername();
        if (username != null) {
            criteria.andAdminEqualTo("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        // 开始查询
        List<Log> logList = logMapper.selectByExample(example);
        return logList;
    }
}


