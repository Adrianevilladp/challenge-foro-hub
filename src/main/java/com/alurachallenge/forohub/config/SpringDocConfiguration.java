package com.alurachallenge.forohub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Forum Hub API")
                        .description(
                                """
                                        "API for Forum Hub application,\s
                                        providing CRUD functionalities for topics and responses,\s
                                        along with authentication and admin operations."
                                        """
                        )
                        .contact(new Contact()
                                .name("Backend team")
                                .email("backend@forumhub.com"))
                        .license(new License()
                                .name("For Practice Use Only")
                                .url("http://forumhub/api/license")));  }
}
