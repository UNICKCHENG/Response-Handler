Hi, there 👇

我正在写一个 Maven 依赖, 以便于在使用 spring 进行开发时节省重复的工作量。

![](assets/restful-api.gif)

## ✨特征

- [X] RESTful API 风格响应体格式
- [X] 支持自动捕获常见异常，无需在抛出异常后再手动 catch
- [ ] 支持返回数据进行加密
- [ ] 支持传参进行解密
- [X] 支持扩展自定义状态码
- [ ] 支持一些常见的断言判断

## 🎉用法

### Step 1 引入依赖

您需要先在 pom.xml 中添加下述依赖，然后命令行使用 `mvn install` 进行下载
```xml
<dependency>
    <groupId>com.github.unickcheng</groupId>
    <artifactId>response-handler-core</artifactId>
    <version>0.0.1</version>
</dependency>
```
> ⚠️ASAP: 目前暂未提交到中央仓库，请在 pom.xml 中增加「GitHub 仓库地址」来获取依赖
```xml
<!--添加 GitHub 仓库地址-->
<repositories>
    <repository>
        <id>unickcheng</id>
        <url>https://unickcheng.github.io/maven-packages/</url>
    </repository>
</repositories>
```
### Step 2 在 Controller 层追加注解

当您在 Controller 层增加 `@RHandlerResponseBody` 注解，将会对当前接口进行封装
```java
@RHandlerResponseBody
```

您也可以使用下面的注解, 等价于 `@RestController` + `@RHandlerResponseBody`
```java
@RHandlerController
```

如果您不知道如何开始，您可以参考或使用 [spring-boot-demo](spring-boot-demo) 来熟悉使用流程


## 扩展玩法

### 1. 根据业务自定义响应体状态码信息

可参考 [ReturnStatus.java](response-handler-demo/src/main/java/cc/unickcheng/rhdemo/enums/ReturnStatus.java) 对 `ResponseStatus` 接口进行覆写，之后只需在相应的方法内抛出自定义异常即可
```java
throw new CommonException(ReturnStatus.CUSTOM_ERROR);
```

如果您暂时没有这方面的需求，建议您使用 `org.springframework.http.HttpStatus` 作为响应体状态码进行快速开发。简单来说，您无需增加额外的操作，只需在相应的方法内抛出类似于下述代码的异常
```java
throw new CommonException(HttpStatus.BAD_REQUEST);
```
