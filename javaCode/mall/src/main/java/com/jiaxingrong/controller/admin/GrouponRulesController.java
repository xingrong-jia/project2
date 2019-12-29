package com.jiaxingrong.controller.admin;

import com.jiaxingrong.bean.type.GrouponRules;
import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.GrouponRulesService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:40
 * @Version 1.0
 */

@RestController
@RequestMapping("admin")
public class GrouponRulesController {
    @Autowired
    GrouponRulesService grouponRulesService;

    /**
     * 团购规则-显示搜索
     * @param map
     * @return
     */
    @RequestMapping("groupon/list")
    public ResultVo grouponRulesList(@RequestParam Map<String,String> map) throws AdEx {
        DataVo grouponList = grouponRulesService.grouponRulesList(map);
        return ResultVoTools.successRe(grouponList,"加载团购规则成功");
    }

    /**
     * 添加团购规则
     * @param grouponRules
     * @return
     * @throws AdEx
     */
    @RequestMapping("groupon/create")
    public ResultVo grouponRulesCreate(@RequestBody GrouponRules grouponRules) throws AdEx {
        setLimit(grouponRules);
        GrouponRules grouponCreate = grouponRulesService.grouponRulesCreate(grouponRules);
        if(grouponCreate != null){
            return ResultVoTools.successRe(grouponCreate,"添加团购规则成功");
        }
        return ResultVoTools.failedRe("添加团购规则失败");
    }

    /**
     * 修改团购规则
     * @param grouponRules
     * @return
     * @throws AdEx
     */
    @RequestMapping("groupon/update")
    public ResultVo grouponRulesUpdate(@RequestBody GrouponRules grouponRules) throws AdEx {
        setLimit(grouponRules);
        GrouponRules grouponUpdate = grouponRulesService.grouponRulesUpdate(grouponRules);
        if(grouponUpdate != null){
            return ResultVoTools.successRe(grouponUpdate,"编辑团购规则成功");
        }
        return ResultVoTools.failedRe("编辑团购规则失败");
    }

    /**
     * 删除团购规则
     * @param grouponRules
     * @return
     */
    @RequestMapping("groupon/delete")
    public ResultVo grouponRulesDelete(@RequestBody GrouponRules grouponRules){
        Integer grouponDelete = grouponRulesService.grouponRulesDelete(grouponRules);
        if(grouponDelete != -1){
            return ResultVoTools.successRe(grouponDelete,"删除团购规则成功");
        }
        return ResultVoTools.failedRe("删除团购规则大失败");
    }

    public void setLimit(GrouponRules grouponRules) throws AdEx {
        if(grouponRules.getGoodsId() < 0){
            throw new AdEx("商品id要大于零");
        }
        if(grouponRules.getDiscount().intValue() < 0){
            throw new AdEx("团购折扣要大于零");
        }
        if(grouponRules.getDiscountMember() < 1){
            throw new AdEx("参团人数要大于1");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date time = null;
        try {
            time = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(grouponRules.getExpireTime().before(time)){
            throw new AdEx("过期时间要晚于当前时间");
        }
    }
}
