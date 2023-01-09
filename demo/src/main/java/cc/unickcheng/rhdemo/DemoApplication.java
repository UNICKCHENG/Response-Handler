/*
 * create on 2023-01-09
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author unickcheng
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.github.unickcheng.rhandler",
        "cc.unickcheng.rhdemo"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info(">>> {}", DemoApplication.class.getSimpleName().toUpperCase() + " STARTING SUCCESS");
    }
}