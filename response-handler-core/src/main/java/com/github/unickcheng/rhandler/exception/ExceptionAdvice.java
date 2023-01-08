/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.exception;

import com.github.unickcheng.rhandler.response.ResponseResult;
import com.github.unickcheng.rhandler.utils.LogInfo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author unickcheng
 */

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAdvice {

    // 参数校验时不符合要求的异常 针对 JavaBean参数校验（form-data）
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseResult bindException(BindException e) {
        LogInfo.exceptionWarn(e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseResult.badRequest().message(null == fieldError ? "":fieldError.getDefaultMessage()).build();}
    // 参数校验时不符合要求的异常 针对 JavaBean参数校验（json）
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        LogInfo.exceptionWarn(e);
        List<String> errorInformation = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseResult.badRequest().message(errorInformation.toString()).build();
    }
    // 参数校验时不符合要求的异常 javax.validation.ConstraintViolationException 针对单一参数校验
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult constraintViolationException(ConstraintViolationException e) {
        LogInfo.exceptionWarn(e);
        Set<ConstraintViolation<?>> content = e.getConstraintViolations();
        StringBuilder errorMsg = new StringBuilder();
        content.forEach(ex -> errorMsg.append(e.getMessage()));
        return ResponseResult.badRequest().message(errorMsg.toString()).build();
    }
}

