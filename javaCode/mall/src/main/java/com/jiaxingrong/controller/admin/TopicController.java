package com.jiaxingrong.controller.admin;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.bean.type.Topic;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.TopicService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 14:54
 * @Version 1.0
 */

@RestController
@RequestMapping("admin")
public class TopicController {
    @Autowired
    TopicService topicService;

    /**
     * 显示和搜索
     * @param map
     * @return
     */
    @RequestMapping("topic/list")
    public ResultVo topicList(@RequestParam Map map){
        DataVo topicList = topicService.topicList(map);
        return ResultVoTools.successRe(topicList, "加载专题成功");
    }

    /**
     * 添加专题
     * @param topic
     * @return
     */
    @RequestMapping("topic/create")
    public ResultVo topicCreate(@RequestBody Topic topic) throws AdEx {
        setLimit(topic);
        Topic topicCreate = topicService.topicCreate(topic);
        if(topicCreate != null) {
            return ResultVoTools.successRe(topicCreate, "添加专题成功");
        }
        return  ResultVoTools.failedRe("添加专题失败");
    }

    /**
     * 编辑专题
     * @param topic
     * @return
     */
    @RequestMapping("topic/update")
    public ResultVo topicUpdate(@RequestBody Topic topic) throws AdEx {
        setLimit(topic);
        Topic topicUpdate = topicService.topicUpdate(topic);
        if(topicUpdate != null) {
            return ResultVoTools.successRe(topicUpdate, "编辑专题成功");
        }
        return  ResultVoTools.failedRe("编辑专题失败");
    }

    /**
     * 删除专题
     * @param topic
     * @return
     */
    @RequestMapping("topic/delete")
    public ResultVo topicDelete(@RequestBody Topic topic){
        Integer topicDelete = topicService.topicDelete(topic);
        if(topicDelete == 1){
            return ResultVoTools.successRe(topicDelete, "编辑专题成功");
        }
        return  ResultVoTools.failedRe("删除失败");
    }

    public void setLimit(Topic topic) throws AdEx {
        if(topic.getPrice().intValue() < 0){
            throw new AdEx("商品低价小于零");
        }
    }
}
