package com.neroll.pojo;

public class Result<T> {
    // 200 表示成功，100 表示失败
    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return Result.success("success");
    }

    public static <T> Result<T> success(String message) {
        return Result.success(message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> error() {
        return Result.error("error");
    }

    public static <T> Result<T> error(String message) {
        return Result.error(message, null);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<>(100, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
