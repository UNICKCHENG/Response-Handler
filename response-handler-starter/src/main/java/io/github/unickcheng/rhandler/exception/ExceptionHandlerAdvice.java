/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.exception;

import io.github.unickcheng.rhandler.response.ResponseResult;
import io.github.unickcheng.rhandler.utils.LogInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

/**
 * 通用异常拦截器
 * @see ResponseEntityExceptionHandler 作为其他异常捕获，注意目前只收集了 15 种异常情形
 * @see #exception(Exception, WebRequest) 添加指定的异常拦截样例
 * @see RHandlerException 自定义异常封装类
 * @author unickcheng
 */

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal (@NotNull Exception e,
                                                             @Nullable Object body,
                                                             @NotNull HttpHeaders headers,
                                                             @NotNull HttpStatus status,
                                                             @NotNull WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            LogInfo.exceptionError(e);
            request.setAttribute("javax.servlet.error.exception", e, 0);
        } else {
            LogInfo.exceptionWarn(e);
        }

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(ResponseResult.status(status).message(e.getMessage()).build());
    }

    // 其他异常捕获, 如果不做额外处理, 该方法仍然不能拦截所有异常, 如 404
    @Nullable
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception (Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, null, new HttpHeaders(), status, request);
    }


    // 自定义异常处理
    @NotNull
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull RHandlerException e,
                                                             @NotNull HttpStatus status,
                                                             @NotNull WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            LogInfo.exceptionError(e);
            request.setAttribute("javax.servlet.error.exception", e, 0);
        } else {
            LogInfo.exceptionWarn(e);
        }

        return ResponseEntity
                .status(e.getHttpStatus())
                .headers(new HttpHeaders())
                .body(ResponseResult.customStatus(e.getResponseDomain().getStatus(), e.getResponseDomain().getMessage()).build());
    }

    @Nullable
    @ExceptionHandler(RHandlerException.class)
    public ResponseEntity<Object> exception (RHandlerException e, WebRequest request) {
        return this.handleExceptionInternal(e, e.getHttpStatus(), request);
    }
}
