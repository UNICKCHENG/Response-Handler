/*
 * create on 2023-01-14
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.exception;

import org.springframework.http.HttpStatus;

/**
 * 统一响应体发生异常时状态码枚举类接口
 * @author unickcheng
 */
public interface ExceptionStatus {
    /**
     * 返回体状态码
     * @return Integer
     */
    int getCode();

    /**
     * 返回体状态提示信息
     * @return String
     */
    String getMessage();

    /**
     * Http 请求返回状态
     * @return HttpStatus
     */
    HttpStatus getHttpStatus();
}
