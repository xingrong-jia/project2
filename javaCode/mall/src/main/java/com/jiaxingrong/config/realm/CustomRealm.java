package com.jiaxingrong.config.realm;

import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminExample;
import com.jiaxingrong.model.Permission;
import com.jiaxingrong.model.PermissionExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        String credential;
        credential = adminList.size() >= 1 ? adminList.get(0).getPassword() : null;
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();

        // 先查询到role_ids，再去查permission
        List<String> permissions = queryAdminAuthorizationInfoByAdminName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    private List<String> queryAdminAuthorizationInfoByAdminName(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        // 如果设置了UNIQUE这里可能要改
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        Integer[] role_ids = adminList.size() >= 1 ? adminList.get(0).getRoleIds(): null;
        List<Integer> role_idList = new ArrayList<>();
        for (Integer i : role_ids) {
            role_idList.add(i);
        }
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria1 = permissionExample.createCriteria();
        criteria1.andRoleIdIn(role_idList);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<String> permissions = new ArrayList<>();
        for (Permission p : permissionList) {
            permissions.add(p.getPermission());
        }
        return permissions;
    }


}
