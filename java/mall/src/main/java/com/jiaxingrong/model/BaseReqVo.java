package com.jiaxingrong.model;

import lombok.Data;

@Data
public class BaseReqVo<T> {

    T data;

    String errmsg;

    int errno;
}
