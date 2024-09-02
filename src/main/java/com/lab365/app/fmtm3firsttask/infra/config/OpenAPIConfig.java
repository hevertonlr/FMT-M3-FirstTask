package com.lab365.app.fmtm3firsttask.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI(){
        Info info = new Info()
                .title("Caixa de Sugestões")
                .version("1.0")
                .description("Este é um exemplo de API que simula uma caixa de sugestões anônima.");
        return new OpenAPI().info(info);
    }
}
