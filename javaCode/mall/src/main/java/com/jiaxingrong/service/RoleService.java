package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByPage(Laypage laypage);

    Role createRole(Role role);

    Integer updateRole(Role role);

    Integer deleteRole(Role role);
}
