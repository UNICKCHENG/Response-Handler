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
 * create on 2023-01-27
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author unickcheng
 * @since 0.2.0
 */

@Component
public class SwaggerConfig {
    @Bean
    public OpenAPI defauoltCustomOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("unickcheng demo api")
                        .version("0.2")
                        .description( "This is based on swagger 3. You can see more details at https://github.com/UNICKCHENG/Response-Handler"));
    }
}