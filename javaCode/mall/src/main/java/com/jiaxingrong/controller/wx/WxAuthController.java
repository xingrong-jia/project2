package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.User;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public BaseRespVo login(@RequestBody User user) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setData("abc");
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
