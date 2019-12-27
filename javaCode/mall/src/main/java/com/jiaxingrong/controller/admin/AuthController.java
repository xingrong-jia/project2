package com.jiaxingrong.controller.admin;

import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

       @RequestMapping("login")
//    public BaseRespVo login(@RequestBody LoginBean loginBean)
    public BaseReqVo login(@RequestBody Map map){
        //没有使用shiro的情况下 ，返回任意的这个值就行了
        //errno = 0
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData("abc");
        baseReqVo.setErrmsg("成功");
        return baseReqVo;

    }


    @RequestMapping("info")
    public String info(){
        String resp = "{\"errno\":0,\"data\":{\"roles\":[\"超级管理员\"],\"name\":\"admin123\",\"perms\":[\"*\"],\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"},\"errmsg\":\"成功\"}";
        return resp;
    }
}
