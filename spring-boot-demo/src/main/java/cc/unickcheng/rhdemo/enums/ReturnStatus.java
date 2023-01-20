/*
 * create on 2023-01-13
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.enums;

import io.github.unickcheng.rhandler.exception.ExceptionStatusInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定业务状态码。
 * 您可以自己创建一个枚举类，来实现 {@link ExceptionStatusInfo} 接口。
 * 之后直接通过 {@link io.github.unickcheng.rhandler.exception.RHandlerException} 来抛出异常<p/>
 *
 * 请务必添加这三组成员<br\>
 * - HttpStatus httpStatus: HTTP 状态码（不同于返回体中的状态码） <br\>
 * - int code: 返回体中的状态码  <br\>
 * - String message: 返回体中的提示信息  <br\> <br\>
 *
 * @author unickcheng
 */

@Getter
@AllArgsConstructor
public enum ReturnStatus implements ExceptionStatusInfo {

    CUSTOM_ERROR(HttpStatus.BAD_REQUEST, 40777, "There is an error!"),
    CUSTOM_ERROR_B(40778, "There is an error!");

    ReturnStatus(int code, String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = code;
        this.message = message;
    }

    // 请务必添加这三组成员
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
