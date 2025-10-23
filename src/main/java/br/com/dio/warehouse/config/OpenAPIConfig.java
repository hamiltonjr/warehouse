package br.com.dio.warehouse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI (Swagger) para documentação da API do Warehouse.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Cria e configura o bean OpenAPI com informações básicas.
     * Serve para geração automática da documentação Swagger.
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API do Estoque do E-commerce") // título da API
                        .version("1.0")                        // versão da API
                        .description("Documentação da API do estoque do e-commerce.")); // descrição
    }
}
