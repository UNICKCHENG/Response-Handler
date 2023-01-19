/*
 * create on 2023-01-18
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.enums;

import io.github.unickcheng.rhandler.plugin.status.annotation.RHandlerExceptionStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 通过注解方式来自定业务状态码。
 * IDEA 可能会给出一些警告或错误提示，请忽略它们 <br\> <br\>
 * HttpStatus httpStatus: HTTP 状态码（不同于 Body 中的状态码） <br\>
 * int code: 返回体中的状态码  <br\>
 * String message: 返回体中的提示信息  <br\> <br\>
 * @author unickcheng
 */

@RHandlerExceptionStatusEnum
@AllArgsConstructor
public enum ErrorStatus {

    OTHER_ERROR_A(HttpStatus.BAD_REQUEST, 30777, "There is an error!");
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
