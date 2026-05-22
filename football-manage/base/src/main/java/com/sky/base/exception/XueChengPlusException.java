package com.sky.base.exception;


import lombok.Data;

/**
 * @description 学成在线项目异常类
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */

@Data
public class XueChengPlusException extends RuntimeException {

    // 此类用于记录异常信息，会被GlobalExceptionHandler捕捉到并处理

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }


}