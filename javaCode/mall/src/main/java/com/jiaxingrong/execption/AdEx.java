package com.jiaxingrong.execption;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/26 20:40
 * @Version 1.0
 */

@Data
public class AdEx extends Exception{
    String msg;

    public AdEx() {
    }

    public AdEx(String msg) {
        this.msg = msg;
    }

}
