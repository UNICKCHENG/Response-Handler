/*
 * create on 2023-01-11
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhandler.annotation;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author unickcheng
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@ComponentScan(basePackages = "com.github.unickcheng.rhandler")
public @interface RHandlerResponseBody {
}
