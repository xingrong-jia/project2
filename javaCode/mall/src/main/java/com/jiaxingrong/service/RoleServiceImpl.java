package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.jiaxingrong.mapper.RoleMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByPage(Laypage laypage) {
        // 开启分页
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        RoleExample example = new RoleExample();
        // 开始设置限制条件
        example.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        // 用example弄出它的内部类
        RoleExample.Criteria criteria = example.createCriteria();
        // 取出name
        String username = laypage.getName();
        if (username != null) {
            criteria.andNameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        // 开始查询
        List<Role> roleList = roleMapper.selectByExample(example);
        return roleList;
    }

    @Override
    public Role createRole(Role role) {
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        // 数据库中deleted的默认值为0，所以新增的时候不用setDeleted
        // role.setDeleted(false);
        roleMapper.insertAndGenerateId(role);
        return role;
    }

    @Override
    public Integer updateRole(Role role) {
        role.setUpdateTime(new Date());
        int update = roleMapper.updateByPrimaryKey(role);
        return update;
    }

    @Override
    public Integer deleteRole(Role role) {
        role.setDeleted(true);
        int update = roleMapper.updateByPrimaryKey(role);
        return update;
    }
}
