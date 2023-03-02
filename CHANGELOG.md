## [0.2.2](https://github.com/UNICKCHENG/Response-Handler/compare/v0.2.1...v0.2.2) (2023-03-02)



## [0.2.1](https://github.com/UNICKCHENG/Response-Handler/compare/v0.2.0...v0.2.1) (2023-03-02)



# [0.2.0](https://github.com/UNICKCHENG/Response-Handler/compare/v0.2.0-SNAPSHOT...v0.2.0) (2023-01-27)

```xml
<dependency>
    <groupId>io.github.unickcheng</groupId>
    <artifactId>response-handler-starter</artifactId>
    <version>0.2.0</version>
</dependency>
```

### Bug Fixes

* **annotation:** close @NotNULL annotation warning ([5a39b03](https://github.com/UNICKCHENG/Response-Handler/commit/5a39b036353b0374a19d3ce54b3994d4dc8da7ef))
* **command:** fix mvn spring-boot:run not be supported ([d3f8793](https://github.com/UNICKCHENG/Response-Handler/commit/d3f8793b0835e4347ebc429166efaab0d446d2ff))
* **javadoc:** GitHub repository is not supported ([03877fa](https://github.com/UNICKCHENG/Response-Handler/commit/03877fa68c967704d22db40c27537dcedcdd15ee))
* **unknown:** fix a warning about javax.annotation.meta.When.MAYBE ([ae2e961](https://github.com/UNICKCHENG/Response-Handler/commit/ae2e961a1db7df8701e49969dc10b9c697133bf8))

# [0.2.0-SNAPSHOT](https://github.com/UNICKCHENG/Response-Handler/compare/v0.0.1...v0.2.0-SNAPSHOT) (2023-01-20)

Hi, the response-handler-start module has been added and the import method has been changed to the following. Please do not use the latest version by importing response-handler-core

```xml
<dependency>
    <groupId>io.github.unickcheng</groupId>
    <artifactId>response-handler-start</artifactId>
    <version>0.2.0-SNAPSHOT</version>
</dependency>
```

### Bug Fixes

* **demo:** fix the Chinese and English system output ([fb24fc8](https://github.com/UNICKCHENG/Response-Handler/commit/fb24fc872418e3adb5fc5b162931f76ba428f96f))
* **exception:** fix bug caused by code adjustment ([1273235](https://github.com/UNICKCHENG/Response-Handler/commit/12732356188bff7c775493b88d0c9a12215b156b))


### Features

* **custom-status-code:**  add the use of annotations ([81e0799](https://github.com/UNICKCHENG/Response-Handler/commit/81e07993f1c0d680070052c110a184f1e371959d))
* **demo:** add jUint test ([985b10a](https://github.com/UNICKCHENG/Response-Handler/commit/985b10af0b4d65ad668c946feda569a19334bda2))
* **start:** add the response-handler-start module ([0c82dae](https://github.com/UNICKCHENG/Response-Handler/commit/0c82daec89a9fc13d5188bfe6019a101d2839ef3))



## 0.0.1 (2023-01-15)


### Bug Fixes

* **$version:** unified management of version number ([1aef008](https://github.com/UNICKCHENG/Response-Handler/commit/1aef0086fabddadf7507e564960d2319ebf55332))
* **annotation:** fix exception handling invalid ([4a3f0a8](https://github.com/UNICKCHENG/Response-Handler/commit/4a3f0a83411d7344a8bb30c3dafac1b007497964))
* **ResponseDomain:** fix text/plain type requests ([c99ea6c](https://github.com/UNICKCHENG/Response-Handler/commit/c99ea6c0a91ce7e47d462e26566a5e1d288e9984))
* **Response:** fix response body return string issue ([a320648](https://github.com/UNICKCHENG/Response-Handler/commit/a32064842e8e2ed435b73d459e91c4caccdb386a)), closes [#74](https://github.com/UNICKCHENG/Response-Handler/issues/74)
* **ResponseStatus:** exception message is repeatedly displayed ([21913af](https://github.com/UNICKCHENG/Response-Handler/commit/21913af97695fd63e081ebfd5443df21198c6af1)), closes [#75](https://github.com/UNICKCHENG/Response-Handler/issues/75)


### Features

* add @RHandlerResponseBody Annotation ([dbd9382](https://github.com/UNICKCHENG/Response-Handler/commit/dbd93826ba072def67d608bea46b8bd9414e25b1))
* **annotation:** add 404 exception handler ([0beea70](https://github.com/UNICKCHENG/Response-Handler/commit/0beea70ebffcd123e77c2b1defd8b469912f072b))
* **annotation:** add RHandlerController annotation ([582bd28](https://github.com/UNICKCHENG/Response-Handler/commit/582bd287deccc5070a8d996eb26720283fdd122c))
* **core:** add custom response body, exception handling, swagger 3. see gh-58 ([4d4b223](https://github.com/UNICKCHENG/Response-Handler/commit/4d4b2235226fc82fac2478d4f5e06ef24699d1a6)), closes [#58](https://github.com/UNICKCHENG/Response-Handler/issues/58)
* **demo:** jdk version changed to 1.8 ([3023f17](https://github.com/UNICKCHENG/Response-Handler/commit/3023f17eef999e5006ec0ad3fa91438ce22f74f6))
* **ResponseDomain:** add custom time zone ([88328b6](https://github.com/UNICKCHENG/Response-Handler/commit/88328b68e35213b2010d05507805b5ed347a1248))
* **ResponseStatus:** support for custom status code extensions ([9301ef2](https://github.com/UNICKCHENG/Response-Handler/commit/9301ef293d796d500927c5b4bbb1f4f60c8d5d18)), closes [#68](https://github.com/UNICKCHENG/Response-Handler/issues/68)


### Performance Improvements

* simplified some code logic ([c351f29](https://github.com/UNICKCHENG/Response-Handler/commit/c351f2989f0237ae6ac60b8a2cd3e9e6b62202a8))
* simplify project structure ([751a4db](https://github.com/UNICKCHENG/Response-Handler/commit/751a4db4a3f3456bc86aa970cbc4f9bf8989153d))



