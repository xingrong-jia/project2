package com.jiaxingrong.controller.admin;

import com.jiaxingrong.config.token.MallToken;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.AdminService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody Map map){
        //没有使用shiro的情况下 ，返回任意的这个值就行了
        //errno = 0
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        Subject subject = SecurityUtils.getSubject();
        MallToken token = new MallToken(username, password, "admin");
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        // AuthenticationException是一个运行时异常，需要自己try catch，IDEA不会提示
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("你的用户名或密码不对哦");
            return baseReqVo;
        }

        baseReqVo.setErrno(0);
        baseReqVo.setData(subject.getSession().getId());
        baseReqVo.setErrmsg("成功");
        return baseReqVo;

    }



    @RequestMapping("info")
    public BaseRespVo info(String token){
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        Map map = adminService.getAdminByTokenUsername(admin);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(map);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("logout")
    public BaseRespVo logout() {
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(null);
        respVo.setErrmsg("成功");
        return respVo;
    }
}
