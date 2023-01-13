/*
 * create on 2023-01-06
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * 已实现返回拦截，无需在控制器中再调用 ResponseResult，详细见 {@link ResponseAdvice}  <br/>
 * 已实现链式调用，调用入口见 {@link #status(HttpStatus)} <br/>
 * 提供三种根据具体场景下的调用 {@link #success()} {@link #success(Object)} {@link #badRequest()} <br/>
 * 目前支持 {@link org.springframework.http.HttpStatus} 和 {@link ResponseStatus} 作为响应体状态码使用
 * @see ResponseDomain 返回体类
 * @see org.springframework.http.ResponseEntity 您也可以直接这个使用现成的封装
 * @author unickcheng
 */

public class ResponseResult extends ResponseDomain {

    ResponseResult(int status, String message, Object data) {
        super(status, message, data);
    }

    // POST, PUT, DELETE 请求成功
    public static ResponseResult success() {
        return status(HttpStatus.OK).build();
    }
    // GET 请求成功
    public static ResponseResult success(Object data) {
        return status(HttpStatus.OK).build(data);
    }
    // 400 请求响应失败
    public static Builder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }

    // Builder 模式入口
    public static Builder status(HttpStatus status) {
        Assert.notNull(status,
                "HttpStatus must not be null. Please see org.springframework.http.HttpStatus");
        return new DefaultBuilder(status);
    }
    public static Builder status(ResponseStatus status) {
        Assert.notNull(status,
                "ResponseStatus must not be null. Please see com.github.unickcheng.rhandler.enums.ResponseStatus");
        return new DefaultBuilder(status);
    }
    public static Builder status(int code) {
        return status(HttpStatus.valueOf(code));
    }

    // 构造链式方法

    public interface Builder {
        Builder message(String message);
        Builder setMessage(String message);
        ResponseResult build(Object data);
        ResponseResult build();
    }

    private static class DefaultBuilder implements Builder {
        private final int status;
        private String message;

        DefaultBuilder(HttpStatus status) {
            this.status = status.value();
            this.message = status.getReasonPhrase();
        }
        DefaultBuilder(ResponseStatus status) {
            this.status = status.getCode();
            this.message = status.getMessage();
        }

        @Override
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder message(String message) {
            this.message = this.message + ". " + message;
            return this;
        }

        @Override
        public ResponseResult build(Object data) {
            return new ResponseResult(this.status, this.message, null == data ? new HashMap<>() : data);
        }

        @Override
        public ResponseResult build() {
            return new ResponseResult(this.status,this.message, null);
        }
    }
}