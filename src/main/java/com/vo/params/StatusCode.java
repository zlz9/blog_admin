package com.vo.params;

/**
 * @author zlz
 * @data 2022/4/11
 */

public enum StatusCode {
    /**
     * 状态码
     */
    SUCCESS(200,"操作成功"),
    /***
     * 错误码
     */
    SYSTEM_ERROR(-999, "系统错误"),
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户密码不存在喔!"),
    TOKEN_ERROR(10003,"Token不合法"),
    ACCOUNT_EXIST(10004,"账号已存在"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    UPLOAD_FAIL(20001, "上传失败");


    private int code;
    private String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
