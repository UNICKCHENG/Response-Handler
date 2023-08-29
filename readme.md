
<h1 align="center">Welcome to Response-Handler 👋</h1>

## Update 2023-08-29

To achieve unified encapsulation and enable Swagger, you just need to import the following dependencies in your `pom.xml`. The more usage can be found in the [spring-boot-demo](./spring-boot-demo).

```xml
<dependency>
    <groupId>io.github.unickcheng</groupId>
    <artifactId>response-handler-starter</artifactId>
    <version>0.3.0</version>
</dependency>
```

---

[![publish](https://img.shields.io/github/actions/workflow/status/UNICKCHENG/Response-Handler/publish.yml?label=publish&style=plastic)](https://github.com/UNICKCHENG/Response-Handler/actions/workflows/publish.yml) [![](https://img.shields.io/github/package-json/v/UNICKCHENG/Response-Handler?color=blue&label=version&style=plastic)](https://github.com/UNICKCHENG/Response-Handler/tags)

[中文版](readme-zh.md)

![](assets/restful-api.gif)

Response-Handler is a unified interface the response body format for Spring developers. You can use a single annotation to take care of exception catching, data return, and OpenAPI 3. If you are interested in this project, feel free to incubate it together, details can be found in [How to contribute](contributing.md).

## ✨ Features

- [x] Unified response body format
- [x] Support automatic catching of common exceptions, reducing the need to manually catch exceptions after they are thrown
- [x] Support for extended custom status codes
- [x] Built-in support for OpenAPI 3, i.e. Swagger 3
- [x] Available for download from the Maven central repository ([OSSRH-87858](https://issues.sonatype.org/projects/OSSRH/issues/OSSRH-87858))
- [ ] Support encryption of returned data (later consider using annotations to build in AES, RSA, SM and other encryption methods)

## 🎉 Usage

You need to add the following dependency to pom.xml and then run `mvn install` to download this dependency, note that the version number may be out of date, you can check the latest version in the [Maven central repository](https://mvnrepository.com/artifact/io.github.unickcheng/response-handler-starter)

```xml
<dependency>
    <groupId>io.github.unickcheng</groupId>
    <artifactId>response-handler-starter</artifactId>
    <version>0.3.0</version>
</dependency>
```

If you want to use the latest development version, you can append the GitHub repository address to pom.xml. Note that using the latest development version may have a number of instabilities

```xml
<!--Add the GitHub repository address-->
<repositories>
    <repository>
        <id>unickcheng</id>
        <url>https://unickcheng.github.io/maven-packages/</url>
    </repository>
</repositories>
```

If you don't know how to get started, you can refer to or use [spring-boot-demo](spring-boot-demo/pom.xml) to familiarize yourself with the process

## 😎 Extra play

### 1. web-side view of all interfaces

Please visit `http://<your-ip>:8080/openapi-ui.html` on the web side, if your port number is not the default 8080, please change it

![Pasted image 20230114213227.png](assets/Pasted-image-20230114213227.png)

The response body structure contains the request time, and the default time zone is `Asia/Shanghai`. Since the field is formatted with `@JsonFormat`, you can easily change it in the `application` configuration file, note that you can only change the time zone at the moment

```
# @JsonFormat: set time zone  
spring.jackson.time-zone=Asia/Shanghai
```

### 2. Custom the response body status code information

This part uses the factory design pattern, where you simply implement the `ExceptionStatusInfo` interface to encapsulate a custom status code enumeration class, and then throw a custom exception in the appropriate method, see [ReturnStatus.java](spring-boot-demo/src/main/java/cc/unickcheng/rhdemo/enums/ReturnStatus.java) 

```java
throw new RHandlerException(ReturnStatus.CUSTOM_ERROR);
```

If you don't need this for now, i recommend using `org.springframework.http.HttpStatus` as a status code for quick development. In short, you don't need to add additional actions, just throw exceptions like the following code in the corresponding method

```java
throw new RHandlerException(HttpStatus.BAD_REQUEST);
```

## ✍️ ChangeLog

- [CHANGELOG](CHANGELOG.md)

## 👍 Other interesting projects

- [Sa-Token](https://github.com/dromara/sa-token)
- [encrypt-body-spring-boot-starter](https://github.com/Licoy/encrypt-body-spring-boot-starter)
- [lombok](https://github.com/projectlombok/lombok)

## 💖 Credits

- [Spring Boot](https://spring.io/projects/spring-boot) provides framework support
- [Open Source Dependencies](https://github.com/UNICKCHENG/Response-Handler/network/dependencies) provides support
- Thanks to all open source projects for sharing ideas and techniques