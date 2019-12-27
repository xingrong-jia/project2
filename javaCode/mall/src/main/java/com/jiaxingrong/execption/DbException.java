package com.jiaxingrong.execption;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/26 20:45
 * @Version 1.0
 */
@Data
public class DbException extends Exception {

    private String msg;

    public DbException() {
    }

    public DbException(String msg) {
        this.msg = msg;
    }
}