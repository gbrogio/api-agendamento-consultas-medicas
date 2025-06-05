package com.example.demo.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("v1").pathsToMatch("/api/**") // Ajuste para incluir apenas os caminhos
                                                                            // que começam com /api/
                .packagesToScan("com.example.demo") // Ajuste para o pacote dos seus RestControllers
                .addOpenApiMethodFilter(method -> method.getDeclaringClass().isAnnotationPresent(RestController.class))
                .addOpenApiCustomizer(customOpenApi()).build();
    }

    public OpenApiCustomizer customOpenApi() {
        return openApi -> {
            openApi.getInfo().setTitle("API de Agendamento de Consultas Médicas"); // Renomeia o título
            openApi.getInfo().setVersion("1.0.0"); // Define a versão
            openApi.getInfo().setDescription(
                    "Permite que pacientes agendem consultas médicas, que médicos visualizem suas agendas e que a clínica gerencie a disponibilidade de horários com base em especialidades e regras de negócio pré-definidas."); // Define
                                                                                                                                                                                                                                  // a
                                                                                                                                                                                                                                  // descrição
        };
    }

    @Override
    public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://api-agenconsul.gbrogio.com.br")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowedHeaders("*")
                .allowCredentials(true).maxAge(3600); // 1 hour max age for CORS preflight cache
    }
}
