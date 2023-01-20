/*
 * create on 2023-01-20
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.exception;

import io.github.unickcheng.rhandler.response.ResponseDomain;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 异常处理封装，您可以像 RuntimeException 一样抛出异常 <br/><br/>
 *
 * 您可以直接使用 {@link HttpStatus} 作为现成的状态码来抛出异常.
 * 也可以通过实现  {@link ExceptionStatusInfo} 接口来自定义状态码枚举类  <br/><br/>
 *
 * @author unickcheng
 * @since jdk 1.8
 */

@Getter
public class RHandlerException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ResponseDomain responseDomain;

    /**
     * 构造方法
     * @param httpStatus HTTP 响应状态码（不同于返回体中的状态码）
     * @param responseDomain 返回体内容
     */
    RHandlerException (HttpStatus httpStatus, ResponseDomain responseDomain) {
        this.httpStatus = httpStatus;
        this.responseDomain = responseDomain;
    }

    public RHandlerException () {
        throw new RuntimeException();
    }

    public RHandlerException (String message) {
        throw new RuntimeException(message);
    }

    /**
     * @param status 自定义状态码
     * @param message 修改自定义状态码中的错误提示信息
     * @param isReplaceMessage 是否将 message 覆盖原本的错误信息. true 覆盖, false 在原错误信息后追加
     */
    public RHandlerException (ExceptionStatusInfo status, String message, boolean isReplaceMessage) {
        throw new RHandlerException(
                status.getHttpStatus(),
                new ResponseDomain(status.getCode(), getReplaceMessage(status.getMessage(), message, isReplaceMessage))
        );
    }

    public RHandlerException (ExceptionStatusInfo status) {
        throw new RHandlerException(status.getHttpStatus(), new ResponseDomain(status.getCode(), status.getMessage()));
    }

    public RHandlerException (ExceptionStatusInfo status, String message) {
        throw new RHandlerException(status, message, false);
    }


    /**
     * @param status 使用 HttpStatus 作为状态码
     * @param message 修改状态码错误中的提示信息
     * @param isReplaceMessage 是否将 message 覆盖原本的错误信息. true 覆盖, false 在原错误信息后追加
     */
    public RHandlerException (HttpStatus status, String message, boolean isReplaceMessage) {
        throw new RHandlerException(
                status,
                new ResponseDomain(status.value(), getReplaceMessage(status.getReasonPhrase(), message, isReplaceMessage))
        );
    }

    public RHandlerException (HttpStatus status) {
        throw new RHandlerException(status, new ResponseDomain(status.value(), status.getReasonPhrase()));
    }

    public RHandlerException (HttpStatus status, String message) {
        throw new RHandlerException (status, message, false);
    }


    /**
     * 快速创建异常状态码信息 <br\>
     * 可以使用该方法快速创建，但不推荐。如果需要自定义，请通过 {@link ExceptionStatusInfo} 方式
     * @param httpStatus HTTP 响应状态码（不同于返回体中的状态码）
     * @param code 异常状态码
     * @param message 异常错误提示信息
     */
    @Deprecated
    public RHandlerException (HttpStatus httpStatus, int code, String message) {
        throw new RHandlerException(httpStatus, new ResponseDomain(code, message));
    }


    /**
     * 获取实际的异常错误提示信息
     * @param message 原错误提示信息
     * @param extraMessage 补充的提示信息
     * @param isReplaceMessage 是否将 message 覆盖原本的错误信息. true 覆盖, false 在原错误信息后追加
     * @return 最终的错误提示信息
     */
    private String getReplaceMessage (String message, String extraMessage, boolean isReplaceMessage) {
        return isReplaceMessage ? message : String.format("%s %s", message, extraMessage);
    }
}
