package com.as.users.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springShopOpenAPI(@Value("${application-description}") String appDesc,
                                     @Value("${application-version}") String appVersion,
                                     @Value("${application-title}") String appTitle) {
        return new OpenAPI()
                .info(new Info().title(appTitle)
                        .description(appDesc)
                        .version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}

