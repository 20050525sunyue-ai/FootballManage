package com.sky.base.exception.exception;

import java.io.Serializable;


public class RestErrorResponse implements Serializable {


    // 统一封装使用

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}