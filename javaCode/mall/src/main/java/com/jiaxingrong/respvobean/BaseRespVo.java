package com.jiaxingrong.respvobean;

import lombok.Data;

@Data
public class BaseRespVo<T> {
    T data;

    String errmsg;

    int errno;

    public BaseRespVo(T data, String errmsg, int errno) {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public BaseRespVo() {
    }

    public BaseRespVo(String errmsg, int errno) {
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public static BaseRespVo ok(){
        return new BaseRespVo("成功",0);
    }

    public static BaseRespVo ok(Object o) {
        return new BaseRespVo(o, "成功", 0);
    }
}
