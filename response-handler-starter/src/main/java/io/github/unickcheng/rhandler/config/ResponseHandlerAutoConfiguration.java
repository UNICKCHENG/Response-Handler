/*
 * create on 2023-08-29
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:response-handler.properties")
@ComponentScan(basePackages = "io.github.unickcheng.rhandler")
@Configuration
public class ResponseHandlerAutoConfiguration {
}
