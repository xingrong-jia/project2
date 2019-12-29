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

import java.util.*;

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


    /**
     * Service层：分页获取用户信息（含模糊查询功能）
     * @param adminReqVo
     * @return
     */
    @Override
    public List<Admin> getAdminInformationByPage(AdminReqVo adminReqVo) {
        // 取出起始页数和每页显示条目数
        Integer pageNumber = adminReqVo.getPage();
        Integer size = adminReqVo.getLimit();
        // 开启分页
        PageHelper.startPage(pageNumber, size);
        AdminExample example = new AdminExample();
        // 取出sort
        String sort = adminReqVo.getSort();
        String order = adminReqVo.getOrder();
        // 开始设置限制条件
        example.setOrderByClause(sort + " " + order);
        // 用example弄出它的内部类
        AdminExample.Criteria criteria = example.createCriteria();
        // 取出username
        String username = adminReqVo.getUsername();
        if (username != null) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        // 开始查询
        List<Admin> adminList = adminMapper.selectByExample(example);
        return adminList;
    }

    /**
     * Service层：获取角色信息
     * @return
     */
    @Override
    public List<Map> getRoleInformation() {

        // 查询所有数据
        List<Role> roleList = roleMapper.selectRole();
        // 遍历把值取出来放mapList
        List<Map> mapList = new ArrayList<>();
        Map map;
        for (Role role : roleList) {
            map = new HashMap();
            map.put("value", role.getId());
            map.put("label", role.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * Service层：增加管理员
     * @param admin
     * @return
     */
    @Override
    public Admin createAdminInfo(Admin admin) {
        admin.setAddTime(new Date());
        admin.setUpdateTime(new Date());
        adminMapper.insertAndGenerateId(admin);
        admin.setDeleted(false);
        return admin;
    }

    /**
     * Service层：逻辑删除用户信息
     * @param admin
     * @return
     */
    @Override
    public Integer deleteAdminInfo(Admin admin) {
        admin.setDeleted(true);
        admin.setUpdateTime(new Date());
        int update = adminMapper.updateByPrimaryKey(admin);
        return update;
    }

    /**
     * Service层：更新管理员信息
     * @param admin
     * @return
     */
    @Override
    public Admin updateAdminInfo(Admin admin) {
        admin.setUpdateTime(new Date());
        int update = adminMapper.updateByPrimaryKey(admin);
        return admin;
    }
}


