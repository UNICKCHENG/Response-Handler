/*
 * create on 2023-01-06
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author unickcheng
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDomain {

    @Schema(name = "请求时间", example = "2022-10-20 15:05:12")
    @JsonFormat
    private final Date timestamp;

    @Schema(name = "请求状态码", description = "常见如 200-成功, 400-外部错误, 500-其他错误")
    @NotEmpty(message = "请求状态码 status 不可为空")
    private final int status;

    @Schema(name = "请求返回信息", description = "一般是对状态码的补充信息")
    @NotBlank(message = "请求返回信息 message 不可为空")
    private final String message;

    @Schema(name = "请求返回值", description = "POST PUT DELETE EXCEPTION 等操作不返回该字段")
    private final Object data;

    ResponseDomain(int status, String message, Object data) {
        timestamp = new Date(System.currentTimeMillis());
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

