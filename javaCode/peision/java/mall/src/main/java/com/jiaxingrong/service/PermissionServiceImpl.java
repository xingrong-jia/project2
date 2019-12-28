package com.jiaxingrong.service;

import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.model.Permission;
import com.jiaxingrong.model.PermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Map permissions(Integer roleId) {
        HashMap<String, List> map = new HashMap<>();
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andDeletedEqualTo(false);
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        map.put("systemPermissions",permissions);
        PermissionExample permissionExampleRoleId = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExampleRoleId.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andRoleIdEqualTo(roleId);
        List<Permission> permissionsRoleId = permissionMapper.selectByExample(permissionExampleRoleId);
        ArrayList<String> strings = new ArrayList<>();
        for (Permission permission : permissionsRoleId) {
            if ("*".equals(permission.getPermission())){
                for (Permission permission1 : permissions) {
                    if (!"*".equals(permission.getPermission())){
                        strings.add(permission.getPermission());
                    }
                }
            }
            break;
        }
        map.put("assignedPermissions",strings);

        return map;
    }
}
