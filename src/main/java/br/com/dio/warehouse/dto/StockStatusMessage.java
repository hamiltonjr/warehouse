package br.com.dio.warehouse.dto;

import br.com.dio.warehouse.entity.StockStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * DTO usado para enviar mensagens de status de estoque via RabbitMQ.
 * Indica o status atual de um determinado estoque.
 */
public record StockStatusMessage(

        // Identificador único do estoque
        @JsonProperty("id")
        UUID id,

        // Status atual do estoque (por exemplo, AVAILABLE ou UNAVAILABLE)
        @JsonProperty("status")
        StockStatus status

) {}
