/*
 * create on 2023-08-29
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerTemplateConfiguration {
    @Bean
    public OpenAPI defauoltCustomOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("unickcheng demo api")
                        .version("0.3")
                        .description( "This is based on swagger 3. You can see more details at https://github.com/UNICKCHENG/Response-Handler"));
    }
}
