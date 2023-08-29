/*
 * create on 2023-01-06
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * 返回体格式处理方法类，支持链式调用，调用入口见 {@link #status(HttpStatus)}。
 * 旧的习惯是在控制器中返回 ResponseResult，目前已无需这样操作，详细可见 {@link ResponseAdvice}  <br/>  <br/>
 *
 * 目前默认状态码(status)和状态提示信息(message)与 {@link org.springframework.http.HttpStatus HTTP 状态码 } 保持一致。
 * 当然，如果您有自定义状态码的需求，可以使用 {@link #customStatus} 作为自定义状态码的接口 <br/>  <br/>
 *
 * 提供三种根据具体场景下的调用 {{@link #success(Object)} {@link #badRequest()}
 * @see ResponseDomain 返回体结构
 * @see org.springframework.http.ResponseEntity 更强大的封装
 * @author unickcheng
 */

@SuppressWarnings("unused")
public class ResponseResult extends ResponseDomain {

    ResponseResult(int status, String message, Object data) {
        super(status, message, data);
    }

    // GET POST, PUT, DELETE  请求成功, data 支持 null
    public static ResponseResult success(Object data) {
        return status(HttpStatus.OK).build(data);
    }
    // 400 请求响应失败
    public static Builder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }

    // 自定义状态码和状态信息
    public static Builder customStatus(int code, String message) {
        Assert.notNull(message, "The parameter message cannot be empty.");
        return new DefaultBuilder(code).setMessage(message);
    }

    // Builder 模式入口
    public static Builder status(HttpStatus status) {
        Assert.notNull(status,
                "HttpStatus must not be null. Please see org.springframework.http.HttpStatus");
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
        private String message = "";

        DefaultBuilder(HttpStatus status) {
            this.status = status.value();
            this.message = status.getReasonPhrase();
        }
        DefaultBuilder(int code) {
            this.status = code;
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
            return new ResponseResult(this.status, this.message, data);
        }

        @Override
        public ResponseResult build() {
            return new ResponseResult(this.status, this.message, null);
        }
    }
}