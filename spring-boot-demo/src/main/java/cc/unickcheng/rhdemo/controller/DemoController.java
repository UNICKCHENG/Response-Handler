/*
 * create on 2023-01-10
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.controller;

import cc.unickcheng.rhdemo.enums.ReturnStatus;
import io.github.unickcheng.rhandler.exception.RHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.ArrayList;

/**
 * @author unickcheng
 */

@Slf4j
@Validated
@RestController
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

    @PostMapping("/error/runtime")
    public void error() {
        throw new RuntimeException("RuntimeException !");
    }

    @PostMapping("/error/custom/1")
    public void customException() {
        throw new RHandlerException(HttpStatus.BAD_REQUEST, "There is an error!");
    }

    @PostMapping("/error/custom/2")
    public void customException2() {
        throw new RHandlerException(ReturnStatus.CUSTOM_ERROR);
    }

    @PostMapping("/error/custom/3")
    public void customException3() {
        throw new RHandlerException(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/error/custom/4")
    public void customException4() {
        throw new RHandlerException();
    }

    @PostMapping("/error/custom/5")
    public void customException5() {
        throw new RHandlerException("There is an error!");
    }

    @GetMapping("/num/{id}")
    public Integer cities(@PathVariable("id") @Validated @Positive Integer id) {
        return id;
    }
}