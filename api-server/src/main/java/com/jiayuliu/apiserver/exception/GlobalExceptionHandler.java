package com.jiayuliu.apiserver.exception;

import com.jiayuliu.apiserver.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @restcontroller + @requestbody
//request
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //capture all exceptions
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("server error, contact admin");
    }
}
