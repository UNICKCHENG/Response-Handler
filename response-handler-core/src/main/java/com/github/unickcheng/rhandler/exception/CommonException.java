/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;

/**
 * @author unickcheng
 */

@Getter
public class CommonException extends RuntimeException {
    @NotBlank(message = "异常错误码")
    private final int code;
    @NotBlank(message = "异常错误信息")
    private final String message;

    public CommonException(int code, String message) {
        this.code = HttpStatus.valueOf(code).value();
        this.message = message;
    }

    public CommonException(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

    public CommonException(String message) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}
