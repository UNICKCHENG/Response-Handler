/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 从 0.2.0 版本开始弃用
 * 请使用 {@link RHandlerException} 来代替该类 @2023-11-20
 * @author unickcheng
 */

@Deprecated
@Getter
public class CommonException extends RuntimeException {
    @NotEmpty(message = "异常错误码不可为空")
    private final int code;
    @NotBlank(message = "异常错误信息不可为空")
    private final String message;
    @NotBlank(message = "异常 HTTP 请求状态不可为空")
    private final HttpStatus httpStatus;

    public CommonException(ExceptionStatusInfo status) {
        this.httpStatus = status.getHttpStatus();
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public CommonException(ExceptionStatusInfo status, String message) {
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
