/*
 * create on 2023-01-10
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.controller;

import com.github.unickcheng.rhandler.annotation.RHandlerResponseBody;
import com.github.unickcheng.rhandler.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author unickcheng
 */

@Slf4j
@RestController
@RHandlerResponseBody
public class DemoController {

    @GetMapping("/")
    public String hello() {
        log.info("hello");
        return "hello";
    }

    @GetMapping("/test")
    public ArrayList<Integer> test() {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            data.add(i);
        }
        log.info(String.valueOf(data));
        return data;
    }

    @PostMapping("/error")
    public void error() {
        throw new RuntimeException("RuntimeException");
    }

    @PostMapping("/v2/error")
    public void CustomException() {
        throw new CommonException("There is an error!");
    }
}