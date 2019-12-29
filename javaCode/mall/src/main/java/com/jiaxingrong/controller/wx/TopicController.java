package com.jiaxingrong.controller.wx;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.bean.type.Topic;
import com.jiaxingrong.service.inter.TopicService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:13
 * @Version 1.0
 */

@RestController(value = "wxTopic")
@RequestMapping("wx")
public class TopicController {
    @Autowired
    TopicService topicService;

    /**
     * 专题列表
     * @param map
     * @return
     */
    @RequestMapping("topic/list")
    public ResultVo topicList(@RequestParam Map<String,String> map){
        Map<String,Object> topicList = topicService.wxTopicList(map);
        return ResultVoTools.successRe(topicList,"加载专题列表成功");
    }

    /**
     * 专题详情
     * @param id
     * @return
     */
    @RequestMapping("topic/detail")
    public ResultVo topicDetail(@RequestParam Integer id){
        Map<String,Object> topicDetail = topicService.wxTopicDetail(id);
        return ResultVoTools.successRe(topicDetail,"加载专题详情成功");
    }

    /**
     * 专题推荐
     * @param id
     * @return
     */
    @RequestMapping("topic/related")
    public ResultVo topicRelated(@RequestParam Integer id){
        List<Topic> topicRelated = topicService.wxTopicRelated(id);
        return ResultVoTools.successRe(topicRelated,"加载专题推荐成功");
    }
}
