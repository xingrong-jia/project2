package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Log;

import java.util.List;

public interface LogService {
    List<Log> getLogListByPage(Laypage laypage);
}
