/*
 * create on 2023-01-18
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.plugin.status.annotation;

import java.lang.annotation.*;

/**
 * 使用场景：自定义业务状态码 <br\>
 * 自动生成 Get 方法和增加接口实现，以及导入相关包。
 * @see io.github.unickcheng.rhandler.plugin.status.processor.ExceptionStatusProcessor 对当前注解进行处理
 * @author unickcheng
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface RHandlerExceptionStatusEnum {
}
