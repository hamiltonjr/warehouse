package br.com.dio.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Warehouse.
 * Responsável por inicializar o contexto do Spring Boot.
 */
@SpringBootApplication // Combina @Configuration, @EnableAutoConfiguration e @ComponentScan
public class WarehouseApplication {

    /**
     * Método principal que inicia a aplicação.
     * O Spring Boot cria todo o contexto da aplicação a partir daqui.
     *
     * @param args argumentos passados via linha de comando
     */
    public static void main(String[] args) {
        // Inicializa a aplicação Spring Boot, configurando beans, propriedades e contextos
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
