package com.example.studentmanagementsystem.common;

import cn.hutool.json.JSONUtil;

public enum ErrorEnum {
	// 数据操作错误定义
	ERROR(400,"BAD_REQUEST"), //这表示服务器无法处理请求，因为请求包含无效参数。
	NO_AUTH(401,"UNAUTHORIZED"), //禁止访问(账号密码错误)
	NO_PERMISSION(403,"FORBIDDEN"),//权限不足
	INTERNAL_SERVER_ERROR(500, "ERROR"),//奇怪的错误
	;

	/** 错误码 */
	private final Integer code;

	/** 错误信息 */
	private final String message;

	ErrorEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
