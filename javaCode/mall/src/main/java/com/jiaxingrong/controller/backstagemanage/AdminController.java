package com.jiaxingrong.controller.backstagemanage;

import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminReqVo;
import com.jiaxingrong.model.Log;
import com.jiaxingrong.respvobean.BaseReqVo;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("list")
    public BaseReqVo getAdminInformationByPage( AdminReqVo adminReqVo) {
        // 对请求体的处理
        List<Log> logList = adminService.getAdminInformationByPage(adminReqVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", logList);
        map.put("total", logList.size());
        BaseReqVo<Object> reqVo = new BaseReqVo<>();
        reqVo.setErrno(0);
        reqVo.setData(map);
        reqVo.setErrmsg("成功");
        return reqVo;
    }
}
