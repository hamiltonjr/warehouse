package br.com.dio.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuração do client REST para comunicação com o microsserviço Storefront.
 */
@Configuration
public class WarehouseClientConfig {

    /**
     * Cria um RestClient configurado com o base-path do Storefront.
     * Permite que o Warehouse faça requisições HTTP ao Storefront.
     *
     * @param basePath URL base do microsserviço Storefront definida em application.yml
     * @return RestClient configurado
     */
    @Bean
    RestClient storefrontClient(@Value("${warehouse.base-path}") final String basePath){
        return RestClient.create(basePath);
    }
}
