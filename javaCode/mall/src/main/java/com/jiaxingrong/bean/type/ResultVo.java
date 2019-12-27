package com.jiaxingrong.bean.type;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:19
 * @Version 1.0
 * 返回结果封装
 */
@Data
public class ResultVo<T> {
    private int errno;

    private T data;

    private String errmsg;
}
