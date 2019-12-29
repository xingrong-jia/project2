package com.jiaxingrong.service.inter;

import com.jiaxingrong.bean.type.GrouponRules;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:41
 * @Version 1.0
 */
public interface GrouponRulesService {
    DataVo grouponRulesList(Map<String,String> map) throws AdEx;

    GrouponRules grouponRulesCreate(GrouponRules grouponRules) throws AdEx;

    GrouponRules grouponRulesUpdate(GrouponRules grouponRules);

    Integer grouponRulesDelete(GrouponRules grouponRules);
    //---------------------------------------------------------------------------------------

    Map<String, Object> wxGrouponList(Map<String, String> map);
}
