package com.springboot.lecture.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("스프링부트 연습")
                .description("토이 프로젝트")
                .version("v1");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
