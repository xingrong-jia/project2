package com.jiaxingrong.controller.admin;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.GrouponService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 16:08
 * @Version 1.0
 */
@RestController
@RequestMapping("admin")
public class GrouponController {
    @Autowired
    GrouponService grouponService;

    /**
     * 显示团购活动
     * @param map
     * @return
     */
    @RequestMapping("groupon/listRecord")
    public ResultVo grouponListRecord(@RequestParam Map<String,String> map) throws AdEx {
        DataVo grouponListRecord = grouponService.grouponListRecord(map);
        return ResultVoTools.successRe(grouponListRecord,"加载团购活动列表成功");
    }
}
