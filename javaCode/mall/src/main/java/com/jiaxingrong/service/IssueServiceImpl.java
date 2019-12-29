package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.IssueMapper;
import com.jiaxingrong.model.Issue;
import com.jiaxingrong.model.IssueExample;
import com.jiaxingrong.model.Laypage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    IssueMapper issueMapper;

    /**
     * 显示所有的问题信息
     * @param laypage
     * @return
     */
    @Override
    public Map issueList(Laypage laypage) {
        Map map = new HashMap();
        PageHelper.startPage(laypage.getPage(),laypage.getLimit());
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andDeletedEqualTo(false);
        List<Issue> issueList = issueMapper.selectByExample(issueExample);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issueList);
        long total = issuePageInfo.getTotal();
        map.put("total",total);
        map.put("items",issueList);
        return map;
    }

    /**
     * 根据查询条件question显示信息
     * @param laypage
     * @return
     */
    @Override
    public Map queryIssueList(Laypage laypage) {
        Map map = new HashMap();
        PageHelper.startPage(laypage.getPage(),laypage.getLimit());
        List<Issue> issueList = issueMapper.selectIssuesByLikeQuestion(laypage.getQuestion());
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issueList);
        long total = issuePageInfo.getTotal();
        map.put("total",total);
        map.put("items",issueList);
        return map;
    }

    /**
     * 添加一个新的问题
     * @param issue
     * @return
     */
    @Override
    public Issue issueCreate(Issue issue) {
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issue.setDeleted(false);
        int insert = issueMapper.insert(issue);
        if (insert == 0){
            return null;
        }
        return issue;
    }

    /**
     * 更新一个问题
     * @param issue
     * @return
     */
    @Override
    public Issue issueUpdate(Issue issue) {
        issue.setUpdateTime(new Date());
        int i = issueMapper.updateByPrimaryKey(issue);
        if (i == 0){
            return null;
        }
        return issue;
    }

    /**
     * 删除一个问题
     * 假删除
     * @param issue
     * @return
     */
    @Override
    public boolean issueDelete(Issue issue) {
        issue.setDeleted(true);
        int i = issueMapper.updateByPrimaryKey(issue);
        if (i==0){
            return false;
        }
        return true;
    }


}
