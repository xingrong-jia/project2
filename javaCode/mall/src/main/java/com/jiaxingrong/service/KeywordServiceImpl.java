package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.KeywordMapper;
import com.jiaxingrong.model.Keyword;
import com.jiaxingrong.model.KeywordExample;
import com.jiaxingrong.model.Laypage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    /**
     * 显示所有关键词信息
     *
     * @param laypage
     * @return
     */
    @Override
    public Map keywordList(Laypage laypage) {
        Map map = new HashMap();
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andDeletedEqualTo(false);
        List<Keyword> keywordList = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> pageInfo = new PageInfo<>(keywordList);
        long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", keywordList);
        return map;
    }

    /**
     * 根据key 和 url进行模糊查询
     *
     * @param laypage
     * @return
     */
    @Override
    public Map queryKeywordList(Laypage laypage) {
        Map map = new HashMap();
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        List<Keyword> keywordList = keywordMapper.selectKeyWordByLikeKeyAndUrl(laypage.getKeyword(), laypage.getUrl());
        PageInfo<Keyword> pageInfo = new PageInfo<>(keywordList);
        long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", keywordList);
        return map;
    }

    /**
     * 添加一个关键词信息
     *
     * @param keyword
     * @return
     */
    @Override
    public Keyword keywordCreate(Keyword keyword) {
        keyword.setSortOrder(100);
        keyword.setDeleted(false);
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        int insert = keywordMapper.insert(keyword);
        if (insert == 0){
            return null;
        }
        return keyword;
    }

    /**
     * 更新一个关键词的信息
     * @param keyword
     * @return
     */
    @Override
    public Keyword keywordUpdate(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        int i = keywordMapper.updateByPrimaryKey(keyword);
        if (i==0){
            return null;
        }
        return keyword;
    }

    /**
     * 删除一个关键词的信息
     * 假删 把deleted设置为true
     * @param keyword
     * @return
     */
    @Override
    public boolean keywordDelete(Keyword keyword) {
        keyword.setDeleted(true);
        int i = keywordMapper.updateByPrimaryKey(keyword);
        if (i==0){
            return false;
        }
        return true;
    }


}
