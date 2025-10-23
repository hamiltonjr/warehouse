package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por notificar mudanças de status de estoque para o microserviço storefront via RabbitMQ.
 */
@Service
public class ProductChangeAvailabilityProducerImpl implements IProductChangeAvailabilityProducer {

    private final RabbitTemplate rabbitTemplate; // Template para enviar mensagens RabbitMQ
    private final String exchangeName;           // Nome da exchange usada para envio das mensagens
    private final String routingKeyName;         // Routing key usada para direcionar a mensagem

    /**
     * Construtor com injeção das dependências RabbitTemplate, exchange e routing key.
     * @param rabbitTemplate Template do RabbitMQ
     * @param exchangeName Nome da exchange
     * @param routingKeyName Routing key
     */
    public ProductChangeAvailabilityProducerImpl(
            RabbitTemplate rabbitTemplate,
            @Value("${spring.rabbitmq.exchange.product-change-availability}") String exchangeName,
            @Value("${spring.rabbitmq.routing-key.product-change-availability}") String routingKeyName) {

        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    /**
     * Envia uma mensagem de mudança de status de estoque para a exchange especificada,
     * usando a routing key configurada.
     * @param message Mensagem contendo o id do produto e seu status
     */
    @Override
    public void notifyStatusChange(final StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}
