/*
 * create on 2023-01-13
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.enums;

import io.github.unickcheng.rhandler.exception.ExceptionStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author unickcheng
 */

@AllArgsConstructor
public enum ReturnStatus implements ExceptionStatus {

    CUSTOM_ERROR(HttpStatus.BAD_REQUEST, 40777, "There is an error!");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
