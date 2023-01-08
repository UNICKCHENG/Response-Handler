/*
 * create on 2023-01-08
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package com.github.unickcheng.rhtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author unickcheng
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.github.unickcheng.rhandler",
        "com.github.unickcheng.rhtest"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        log.info(">>> {}", TestApplication.class.getSimpleName().toUpperCase() + " STARTING SUCCESS");
    }
}
