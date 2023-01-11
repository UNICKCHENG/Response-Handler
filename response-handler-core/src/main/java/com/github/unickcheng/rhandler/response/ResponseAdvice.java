/*
 * create on 2023-01-06
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.constraints.NotNull;

/**
 * 拦截所有 ResponseBody
 * @see #beforeBodyWrite 所有被拦截的 ResponseBody 进行额外处理
 * @see #supports 确保哪些情况进行拦截, true 表示拦截, false 表示不拦截, 被拦截则会进入 {@link #beforeBodyWrite 进行处理}
 * @see ResponseBodyAdvice 修改 ResponseBody 的拦截器
 * @see com.github.unickcheng.rhandler.exception.ExceptionHandlerAdvice 关于异常拦截的封装
 * @author unickcheng
 */

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NotNull MethodParameter returnType
            , @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 避免 Swagger 不可用, 因此此处不拦截 Swagger 加载页面时的数据请求
        // 注意 通过 Swagger 向后端接口的请求仍然会被拦截处理, 这也是需要的
        return !String.valueOf(returnType).contains("openapiJson");
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType
            , @NotNull MediaType selectedContentType
            , @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType
            , @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {

        // 1）ResponseResult 对象不必反复封装
        // 2）通过 setStatusCode 保证 HTTP 状态码和返回体状态码一致，主要针对 @ExceptionHandler 拦截的数据
        if (body instanceof ResponseResult) {
            response.setStatusCode(HttpStatus.valueOf(((ResponseResult) body).getStatus()));
            return body;
        }

        // 字符串对象不能直接进行封装，需额外处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(ResponseResult.success(body));
            } catch (JsonProcessingException e) {
                return ResponseResult.badRequest().message("返回字符串类型错误");
            }
        }

        // HTTP 状态码非 200 的情形, 主要针对异常没有被 @ExceptionHandler 成功拦截
        ServletServerHttpResponse res= (ServletServerHttpResponse)(response);
        if (200 != res.getServletResponse().getStatus()) {
            return ResponseResult.status(res.getServletResponse().getStatus()).build();
        }

        // 针对 GET POST PUT DELETE 不同请求处理
        return  "GET".equals(request.getMethodValue()) ? ResponseResult.success(body) : ResponseResult.success();
    }
}

