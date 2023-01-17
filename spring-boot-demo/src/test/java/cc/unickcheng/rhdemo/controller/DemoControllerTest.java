/*
 * create on 2023-01-17
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo.controller;

import cc.unickcheng.rhdemo.ResponseBody;
import cc.unickcheng.rhdemo.enums.ReturnStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

/**
 * API Testing
 * @author unickcheng
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void hello() {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.getForEntity("/", ResponseBody.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getBody().getStatus());
        Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), responseEntity.getBody().getMessage());
        Assertions.assertEquals("hello", responseEntity.getBody().getData());
    }

    @Test
    public void error()  {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.postForEntity("/error", null, ResponseBody.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + ". RuntimeException", responseEntity.getBody().getMessage());
    }

    @Test
    public void CustomException() {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.postForEntity("/v1/error", null, ResponseBody.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        Assertions.assertEquals("There is an error!", responseEntity.getBody().getMessage());
    }

    @Test
    public void CustomExceptionV2() {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.postForEntity("/v2/error", null, ResponseBody.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(ReturnStatus.CUSTOM_ERROR.getCode(), responseEntity.getBody().getStatus());
        Assertions.assertEquals(ReturnStatus.CUSTOM_ERROR.getMessage(), responseEntity.getBody().getMessage());
    }

    @Test
    public void cities() {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.getForEntity("/num/1", ResponseBody.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getBody().getStatus());
        Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), responseEntity.getBody().getMessage());
        Assertions.assertEquals("1", responseEntity.getBody().getData());
    }

    @Test
    public void citiesPositive() {
        ResponseEntity<ResponseBody> responseEntity = testRestTemplate.getForEntity("/num/-1", ResponseBody.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());

        // 临时解决「中英文系统输出不一致问题」
        try{
            Assertions.assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase() + ". cities.id: must be greater than 0", responseEntity.getBody().getMessage());
        } catch (Throwable tr) {
            Assertions.assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase() + ". cities.id: 必须是正数", responseEntity.getBody().getMessage());
        }
    }
}
