package com.neroll.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    // success: 200
    // error: 100
    private Integer code;

    private String message;

    private T data;

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
}
