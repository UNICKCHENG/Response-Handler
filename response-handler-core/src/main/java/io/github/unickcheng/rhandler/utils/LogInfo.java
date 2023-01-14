/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 一些日志功能简单封装
 * @since Java SE Development Kit 11.0.16.1
 * @author unickcheng
 */

@Slf4j
public class LogInfo {

    // 控制台打印异常日志输出
    public static void exceptionWarn(Throwable e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        log.warn("错误信息=[{}]", e.getMessage());
        log.warn("异常信息: 类名={}, 文件名={}, 行数={}, 方法名={}"
                , stackTraceElement.getClassName(), stackTraceElement.getFileName()
                , stackTraceElement.getLineNumber(), stackTraceElement.getMethodName());
    }
    // 控制台打印异常日志输出
    public static void exceptionError(Throwable e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        log.error("错误信息=[{}]", e.getMessage());
        log.error("异常信息: 类名={}, 文件名={}, 行数={}, 方法名={}"
                , stackTraceElement.getClassName(), stackTraceElement.getFileName()
                , stackTraceElement.getLineNumber(), stackTraceElement.getMethodName());
    }
}
