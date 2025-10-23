package br.com.dio.warehouse.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para o microsserviço Warehouse.
 * Cria Queue, Exchange, Binding e RabbitTemplate com conversão JSON.
 */
@Configuration
public class AMQPConfig {

    /**
     * Conversor de mensagens para JSON usando Jackson.
     * Facilita envio/recebimento de DTOs.
     */
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura o RabbitTemplate com conversor JSON.
     * Usado para enviar mensagens para filas.
     */
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                  final Jackson2JsonMessageConverter converter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    /**
     * Cria a fila do RabbitMQ para produtos.
     * Nome da fila definido em application.yml.
     */
    @Bean
    Queue queue(@Value("${spring.rabbitmq.queue.product-change-availability}") final String name) {
        return new Queue(name, true); // true = durável
    }

    /**
     * Cria a exchange direta para produtos.
     * Nome definido em application.yml.
     */
    @Bean
    DirectExchange exchange(@Value("${spring.rabbitmq.exchange.product-change-availability}") final String name) {
        return new DirectExchange(name);
    }

    /**
     * Cria o binding entre a fila e a exchange usando a routing key.
     * @return Binding registrado como bean do Spring
     */
    @Bean
    Binding binding(final Queue queue,
                    final DirectExchange exchange,
                    @Value("${spring.rabbitmq.routing-key.product-change-availability}") final String name) {
        return BindingBuilder.bind(queue).to(exchange).with(name);
    }
}
