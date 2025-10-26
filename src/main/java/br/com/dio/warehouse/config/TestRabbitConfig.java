package br.com.dio.warehouse.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestRabbitConfig {

    @Value("${spring.rabbitmq.exchange.product-change-availability:NA}")
    private String exchangeName;

    @PostConstruct
    public void init() {
        System.out.println("Exchange lida: " + exchangeName);
    }
}
