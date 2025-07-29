package com.example.apache_poi_large_excel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Large Excel Writer API")
                        .version("1.0")
                        .description("Spring Boot application to stream large Excel files using Apache POI SXSSFWorkbook"));
    }
}