package com.jiaxingrong.service;

import com.jiaxingrong.model.*;

import java.util.List;
import java.util.Map;

public interface AdminService {


    List<Admin> getAdminInformationByPage(AdminReqVo adminReqVo);


    List<Map> getRoleInformation();

    Admin createAdminInfo(Admin admin);

    Integer deleteAdminInfo(Admin admin);

    Admin updateAdminInfo(Admin admin);

    Map getAdminByTokenUsername(Admin admin);
}
