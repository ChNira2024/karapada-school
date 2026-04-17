package com.karapada.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "School Management System APIs",
        version = "1.0.0",
        description = "Spring Boot REST APIs with JWT Authentication",
        contact = @Contact(
            name = "Niranjana Charty",
            email = "niranjanacharty2013@gmail.com"
        )
    ),
    security = @SecurityRequirement(name = "bearerAuth")
)
public class SwaggerDocsConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            );
    }
}
