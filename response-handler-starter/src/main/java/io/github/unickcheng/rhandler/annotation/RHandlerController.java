/*
 * create on 2023-01-12
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author unickcheng
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RHandlerResponseBody
public @interface RHandlerController {
    @AliasFor(
            annotation = RestController.class
    )
    String value() default "";
}
