/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.exception;

import com.github.unickcheng.rhandler.response.ResponseResult;
import com.github.unickcheng.rhandler.response.ResponseStatus;
import com.github.unickcheng.rhandler.utils.LogInfo;
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
 * @see CommonException 自定义异常封装类
 * @author unickcheng
 */

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception e,
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

    @NotNull
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception e,
                                                             @Nullable Object body,
                                                             @NotNull HttpHeaders headers,
                                                             @NotNull ResponseStatus status,
                                                             @NotNull WebRequest request) {
        return ResponseEntity
                .status(status.getHttpStatus())
                .headers(headers)
                .body(ResponseResult.status(status).message(e.getMessage()).build());
    }

    // 其他异常和自定义异常捕获, 如果不做额外处理, 该方法仍然不能拦截所有异常, 如 404
    @ExceptionHandler({Exception.class, CommonException.class})
    @Nullable
    public ResponseEntity<Object> exception (Exception e, WebRequest request) {
        if (e instanceof CommonException) {
            ResponseStatus status = ((CommonException) e).getResponseStatus();
            return this.handleExceptionInternal(e, null, new HttpHeaders(), status, request);
        }
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, null, new HttpHeaders(), status, request);
    }
}
