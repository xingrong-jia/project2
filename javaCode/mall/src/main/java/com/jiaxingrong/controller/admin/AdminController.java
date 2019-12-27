package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Ad;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminReqVo;

import com.jiaxingrong.model.Log;
import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 分页获取管理员信息的业务逻辑（含模糊查找）
     * @param adminReqVo
     * @return
     */
    @RequestMapping("admin/list")
    public BaseRespVo getAdminInformationByPage(AdminReqVo adminReqVo) {
        // 对请求体的处理
        List<Admin> adminList = adminService.getAdminInformationByPage(adminReqVo);
        Map<String, Object> map = new HashMap<>();
        map.put("items", adminList);
        map.put("total", adminList.size());
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(map);
        respVo.setErrmsg("成功");
        return respVo;
    }

    /**
     * 添加管理员信息的业务逻辑
     * @param admin
     * @return
     */
    @RequestMapping("admin/create")
    public BaseRespVo createAdminInfo(@RequestBody Admin admin) {
        Admin adminresp = adminService.createAdminInfo(admin);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(adminresp);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("admin/update")
    public BaseRespVo updateAdminInfo(@RequestBody Admin admin) {
        Admin adminresp = adminService.updateAdminInfo(admin);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(adminresp);
        respVo.setErrmsg("成功");
        return respVo;
    }

    /**
     * 逻辑删除的业务逻辑
     * @param admin
     * @return
     */
    @RequestMapping("admin/delete")
    public BaseRespVo deleteAdminInfo(@RequestBody Admin admin) {
        Integer deleted = adminService.deleteAdminInfo(admin);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        return respVo;
    }
}
