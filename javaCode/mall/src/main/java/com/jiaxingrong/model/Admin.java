package com.jiaxingrong.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    Integer id;
    String username;
    String password;
    String lastLoginIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date lastLoginTime;
    String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updateTime;
    Boolean deleted;
    //String roleIds;
    Integer[] roleIds;

}