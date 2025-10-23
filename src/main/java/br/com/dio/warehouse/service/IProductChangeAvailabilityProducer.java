package br.com.dio.warehouse.service;

import br.com.dio.warehouse.dto.StockStatusMessage;

/**
 * Interface responsável por enviar mensagens de alteração de status de produtos
 * para o microserviço Storefront via RabbitMQ.
 */
public interface IProductChangeAvailabilityProducer {

    /**
     * Notifica o Storefront sobre uma mudança de status de estoque.
     * @param message objeto contendo o ID do produto e seu novo status
     */
    void notifyStatusChange(final StockStatusMessage message);
}
