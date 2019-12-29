package com.jiaxingrong.requstov.admin;

import lombok.Data;

import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/26 20:36
 * @Version 1.0
 */
@Data
public class DataVo<T> {

    private long total;

    private List<T> items;
}
