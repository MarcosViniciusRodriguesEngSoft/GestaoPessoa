package com.elotech.gestaopessoaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI happyCodeOpenAPI() {
        final String apiTitle = "Elotech Gestão de Pessoa API";
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .version("v0.0.1")
                        .description("Elotech Gestão de pessoas")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
