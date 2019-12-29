package com.jiaxingrong.model;

import lombok.Data;

@Data
public class AdminReqVo {
    Integer page;

    Integer limit;

    String sort;

    String order;

    String username;
}
