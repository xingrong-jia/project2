package com.jiaxingrong.controller.admin;

import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("admin/profile")
public class  ProfileController {

    @Autowired
    AdminService adminService;


}
