package com.jiaxingrong.execption;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:52
 * @Version 1.0
 */
@Data
public class InputException extends Exception {
    private String msg;

    public InputException() {
    }

    public InputException(String msg) {
        this.msg = msg;
    }
}
