/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.exception;

import com.github.unickcheng.rhandler.response.ResponseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author unickcheng
 */

@Getter
public class CommonException extends RuntimeException {
    @NotEmpty(message = "异常错误码不可为空")
    private final int code;
    @NotBlank(message = "异常错误信息不可为空")
    private final String message;
    @NotBlank(message = "异常 HTTP 请求状态不可为空")
    private final HttpStatus httpStatus;
    @Schema(name = "自定义状态码统一枚举类")
    private ResponseStatus responseStatus = null;

    public CommonException(ResponseStatus status) {
        this.httpStatus = status.getHttpStatus();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.responseStatus = status;
    }

    public CommonException(ResponseStatus status, String message) {
        this.httpStatus = status.getHttpStatus();
        this.code = status.getCode();
        this.message = message;
    }

    public CommonException(HttpStatus status, String message) {
        this.httpStatus = status;
        this.code = status.value();
        this.message = message;
    }

    public CommonException(String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}
