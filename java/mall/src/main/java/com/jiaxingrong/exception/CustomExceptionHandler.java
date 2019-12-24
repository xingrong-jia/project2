package com.jiaxingrong.exception;


import com.jiaxingrong.model.BaseReqVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler{

    @ExceptionHandler(CustomException.class)
    public BaseReqVo handleCustomException(CustomException e){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(500);
        baseReqVo.setErrmsg(e.getMessage());
        return baseReqVo;

    }
}