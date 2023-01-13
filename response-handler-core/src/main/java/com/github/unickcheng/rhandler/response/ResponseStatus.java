/*
 * create on 2023-01-13
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.response;

import org.springframework.http.HttpStatus;

/**
 * 统一响应体状态码枚举类
 * @author unickcheng
 */

public interface ResponseStatus {

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
