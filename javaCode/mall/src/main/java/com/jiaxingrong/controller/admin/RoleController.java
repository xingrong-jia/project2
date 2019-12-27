package com.jiaxingrong.controller.admin;

import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/role")
public class RoleController {
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
}
