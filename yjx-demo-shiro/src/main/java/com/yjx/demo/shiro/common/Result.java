package com.yjx.demo.shiro.common;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(AppResultCode appResultCode) {
        this.code = appResultCode.getCode();
        this.msg = appResultCode.getMsg();
    }

    public Result(AppResultCode appResultCode, T data) {
        this.code = appResultCode.getCode();
        this.msg = appResultCode.getMsg();
        this.data = data;
    }

    public static Result genSuccessResult() {
        return new Result(AppResultCode.SUCCESS);
    }

    public static Result genSuccessResult(Object data) {
        return new Result(AppResultCode.SUCCESS, data);
    }

    public static Result genFailResult(AppResultCode appResultCode) {
        return new Result(appResultCode);
    }

    public static Result genFailResult() {
        return new Result(AppResultCode.FAIL);
    }

    public static Result genFailResult(Object data) {
        return new Result(AppResultCode.FAIL, data);
    }
}
