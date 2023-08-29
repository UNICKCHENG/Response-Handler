/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cc.unickcheng.rhdemo;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * 自定义 Swagger，将会覆盖掉默认的配置
 * 
 * @author unickcheng
 * @since 2023-03-03
 */
@Component
public class SwaggerConfigs {

    @Bean
    public GroupedOpenApi demoApi() {
        return GroupedOpenApi.builder()
                .group("demo-api")
                .pathsToMatch("/**")
                .build();
    }

    @Primary
    @Bean
    public OpenAPI demOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("spring boot demo")
                        .version("0.3")
                        .description(
                                "This is based on swagger 3. You can see more details at https://github.com/UNICKCHENG/Response-Handler"));
    }
}
