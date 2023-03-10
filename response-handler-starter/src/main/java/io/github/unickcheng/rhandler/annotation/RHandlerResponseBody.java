/*
 * create on 2023-01-11
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author unickcheng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Configuration
@PropertySource(value = "classpath:response-handler.properties")
@ComponentScan(basePackages = "io.github.unickcheng.rhandler")
public @interface RHandlerResponseBody {
}
