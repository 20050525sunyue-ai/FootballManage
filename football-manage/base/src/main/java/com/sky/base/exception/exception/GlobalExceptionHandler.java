package com.sky.base.exception.exception;


import com.sky.base.exception.XueChengPlusException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {

    // 此类会捕捉抛出的异常，可使用@ExceptionHandler注解对异常进行分类处理

    @ExceptionHandler(XueChengPlusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(XueChengPlusException e){
        // 记录异常
        log.error("【系统异常】{}",e.getErrMessage(),e);
        // 解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(e.getErrMessage());
        return restErrorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(Exception e){
        // 记录异常
        log.error("【系统异常】{}",e.getMessage(),e);
        // 解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
        return restErrorResponse;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestErrorResponse customException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        // 存放错误信息
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item->{
            errors.add(item.getDefaultMessage());
        });
        // 将errors中的错误信息拼接起来
        String errMessage = StringUtils.join(errors, ",");

        // 记录异常
        log.error("【系统异常】{}",e.getMessage(),errMessage);
        // 解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
        return restErrorResponse;
    }


}
