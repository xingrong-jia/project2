package com.jiaxingrong.service;

import com.jiaxingrong.model.Issue;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface IssueService {
    Map issueList(Laypage laypage);

    Map queryIssueList(Laypage laypage);

    Issue issueCreate(Issue issue);

    Issue issueUpdate(Issue issue);

    boolean issueDelete(Issue issue);
}
