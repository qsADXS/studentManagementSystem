package com.example.studentmanagementsystem.dto;

import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;

public class ErrorDTO {
    private final ErrorEnum errorEnum;
    public ErrorDTO(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

//    @Override
//    public String toString() {
//        return JSONUtil.toJsonStr(this.errorEnum);
//    }


}
