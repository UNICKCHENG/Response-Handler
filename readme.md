Hi, there 👇

我正在写一个 Maven 依赖, 以便于在使用 spring 进行开发时节省重复的工作量。

![](assets/restful-api.gif)

## ✨特征

- [X] RESTful API 风格响应体格式
- [X] 支持自动捕获常见异常，无需在抛出异常后再手动 catch
- [ ] 支持扩展自定义状态码
- [ ] 支持一些常见的断言判断

## 🎉用法

您需要先在 pom.xml 中添加下述依赖，然后命令行使用 `mvn install` 进行下载
```xml
<dependency>
    <groupId>com.github.unickcheng</groupId>
    <artifactId>response-handler-core</artifactId>
    <version>${response-handler.version}</version>
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

如果您不知道如何开始，您可以参考或使用 [demo](https://github.com/UNICKCHENG/Response-Handler/tree/main/demo) 来熟悉使用流程
