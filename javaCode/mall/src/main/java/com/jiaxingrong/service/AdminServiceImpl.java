package com.jiaxingrong.service;

import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.mapper.RoleMapper;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminPassword;
import com.jiaxingrong.model.InfoData;
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

    /**
     * 管理员登录校验
     *
     * @param admin
     * @return
     */
    @Override
    public boolean login(Admin admin) {
        Integer integer = adminMapper.selectIdByUsernameAndPassword(admin);
        if (integer != null) return true;
        return false;
    }

    /**
     * 获取管理员数据
     *
     * @param token
     * @return
     */
    @Override
    public InfoData info(String token) {
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();

        InfoData data = new InfoData();
        data.setAvatar(admin.getAvatar());
        data.setName(admin.getUsername());
        //String roleIds = admin.getRoleIds();
        //Integer[] integers = JsonUtils.convertToObject(roleIds, Integer[].class);
        List<String> roleId = permissionMapper.selectPermissionByRoleId(admin.getRoleIds());
        List<String> strings = roleMapper.selectNameByIds(admin.getRoleIds());
        data.setPerms(roleId);

        data.setRoles(strings);
        return data;
    }

    /**
     * 修改当前登录的管理员账户的密码
     *
     * @param adminPassword
     * @return 1代表修改成功  2代表旧密码错误  3代表新密码和确认新密码不一致 0表示服务器异常
     */
    @Override
    public int changeAdminPassword(AdminPassword adminPassword) {
        if (adminPassword.getNewPassword() != adminPassword.getNewPassword2()) return 3;
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        String password = admin.getPassword();
        if (password != adminPassword.getOldPassword()) return 2;
        admin.setPassword(adminPassword.getNewPassword());
        int update = adminMapper.updateByPrimaryKeySelective(admin);
        return update;
    }
}
