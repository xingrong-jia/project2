package com.jiaxingrong.service;

import com.jiaxingrong.model.*;

import java.util.List;

public interface AdminService {


    List<Log> getAdminInformationByPage(AdminReqVo adminReqVo);
}
