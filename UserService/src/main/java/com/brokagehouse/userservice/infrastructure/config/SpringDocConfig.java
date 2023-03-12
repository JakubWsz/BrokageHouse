package com.brokagehouse.userservice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("brokagehouse")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public OpenAPI applicationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("UserService API")
                        .version("v0.0.1"));
    }
}
