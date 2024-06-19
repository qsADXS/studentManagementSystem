package com.example.studentmanagementsystem.component;

import com.example.studentmanagementsystem.common.ErrorEnum;

public class DefinitionException extends RuntimeException{

    protected ErrorEnum errorEnum;

    public DefinitionException(ErrorEnum errorEnum){
        this.errorEnum = errorEnum;
    }


    public Integer getCode() {
        return errorEnum.getCode();
    }

    public String getMessage() {
        return errorEnum.getMessage();
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }


}
