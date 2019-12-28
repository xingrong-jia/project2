package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Role;
import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.AdminService;
import com.jiaxingrong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    AdminService adminService;

    @RequestMapping("options")
    public BaseRespVo getRoleInformation() {
        List<Map> mapList = adminService.getRoleInformation();
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(mapList);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("list")
    public BaseRespVo getRolesByPage(Laypage laypage) {
        List<Role> roleList = roleService.getRolesByPage(laypage);
        Map<String, Object> map = new HashMap<>();
        map.put("items", roleList);
        map.put("total", roleList.size());
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(map);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("create")
    public BaseRespVo createRole(@RequestBody Role role) {
        Role roleResp = roleService.createRole(role);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(roleResp);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("update")
    public BaseRespVo updateRole(@RequestBody Role role) {
        Integer update = roleService.updateRole(role);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("delete")
    public BaseRespVo deleteRole(@RequestBody Role role) {
        // 逻辑删除
        Integer deleted = roleService.deleteRole(role);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        return respVo;

    }

}
