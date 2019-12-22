package com.jiaxingrong.controller;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.InfoData;
import com.jiaxingrong.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthController {

    @RequestMapping("admin/auth/login")
    public BaseReqVo login(@RequestBody User user){

        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        baseReqVo.setData("4b7d719e-53b7-4019-9677-6309b2445b45");
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("admin/auth/info")
    public BaseReqVo info(String token){
        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("admin");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);

        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
