package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductChangeAvailabilityProducerImpl implements IProductChangeAvailabilityProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routineKeyname;

    public ProductChangeAvailabilityProducerImpl(RabbitTemplate rabbitTemplate,
                                                @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                String exchangeName,
                                                 @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                String routineKeyname) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routineKeyname = routineKeyname;
    }


    public void notifyStatusChange(final StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName,routineKeyname, message);
    }
}
