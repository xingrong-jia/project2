package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Log;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/log")
public class LogController {

    @Autowired
    LogService logService;
    @RequestMapping("list")
    public BaseRespVo getLogListByPage(Laypage laypage) {
        List<Log> logList = logService.getLogListByPage(laypage);
        Map<String, Object> map = new HashMap<>();
        map.put("items", logList);
        map.put("total", logList.size());
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(map);
        respVo.setErrmsg("成功");
        return respVo;
    }

}
