/*
 * create on 2023-01-13
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.enums;

import io.github.unickcheng.rhandler.exception.ExceptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定业务状态码 <br\><br\>
 * HttpStatus httpStatus: HTTP 状态码（不同于 Body 中的状态码） <br\>
 * int code: 返回体中的状态码  <br\>
 * String message: 返回体中的提示信息  <br\> <br\>
 * @author unickcheng
 */

@Getter
@AllArgsConstructor
public enum ReturnStatus implements ExceptionStatus {

    CUSTOM_ERROR(HttpStatus.BAD_REQUEST, 40777, "There is an error!"),
    CUSTOM_ERROR_B(40778, "There is an error!");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ReturnStatus(int code, String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = code;
        this.message = message;
    }

}
