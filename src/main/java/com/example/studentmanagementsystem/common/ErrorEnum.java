package com.example.studentmanagementsystem.common;

public enum ErrorEnum {
	// 数据操作错误定义
	/**
	 * 这表示服务器无法处理请求，因为请求包含无效参数。
	 */
	ERROR(400,"BAD_REQUEST"),
	/**
	 * 这表示禁止访问(账号密码错误)
	 */
	NO_AUTH(401,"UNAUTHORIZED"),
	/**
	 * 权限不足
	 */
	NO_PERMISSION(403,"FORBIDDEN"),
	/**
	 * 奇怪的错误
	 */
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
