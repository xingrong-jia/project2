package com.jiaxingrong.service;

import com.jiaxingrong.model.Keyword;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface KeywordService {
    Map keywordList(Laypage laypage);

    Map queryKeywordList(Laypage laypage);

    Keyword keywordCreate(Keyword keyword);

    Keyword keywordUpdate(Keyword keyword);

    boolean keywordDelete(Keyword keyword);
}
