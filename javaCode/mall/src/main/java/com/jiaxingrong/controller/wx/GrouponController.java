package com.jiaxingrong.controller.wx;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.service.inter.GrouponRulesService;
import com.jiaxingrong.service.inter.GrouponService;
import com.jiaxingrong.tools.ResultVoTools;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:46
 * @Version 1.0
 */

@RestController(value = "wxGroup")
@RequestMapping("wx")
public class GrouponController {
    @Autowired
    GrouponService grouponService;
    @Autowired
    GrouponRulesService grouponRulesService;

    /**
     * 团购列表
     * @param map
     * @return
     */
    @RequestMapping("groupon/list")
    public ResultVo grouponList(@RequestParam Map<String,String> map){
        Map<String,Object> grouponList = grouponRulesService.wxGrouponList(map);
        return ResultVoTools.successRe(grouponList,"加载团购列表成功");
    }

    /**
     * 团购详情
     * @param grouponId
     * @return
     */
    @RequestMapping("groupon/detail")
    @RequiresAuthentication
    public ResultVo grouponDetail(@RequestParam Integer grouponId){
        Map<String,Object> grouponDetail = grouponService.wxGrouponDetail(grouponId);
        return ResultVoTools.successRe(grouponDetail,"加载团购详情成功");
    }

    /**
     * 我的团购
     * @param showType
     * @return
     */
    @RequestMapping("groupon/my")
    @RequiresAuthentication
    public ResultVo grouponMy(@RequestParam Integer showType){
        Map<String,Object> grouponMy = grouponService.wxGrouponMy(showType);
        return ResultVoTools.successRe(grouponMy,"加载团购详情成功");
    }
}
