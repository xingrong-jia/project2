package com.jiaxingrong.execption;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:58
 * @Version 1.0
 */
@Data
public class FileUploadEx extends Exception {

    private String msg;

    public FileUploadEx() {
    }

    public FileUploadEx(String msg) {
        this.msg = msg;
    }
}
