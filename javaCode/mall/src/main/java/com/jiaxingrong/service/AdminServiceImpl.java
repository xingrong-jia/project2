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


    }


