package br.com.dio.warehouse.controller.response;

import br.com.dio.warehouse.entity.StockStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de resposta para indicar que um registro de estoque foi salvo com sucesso.
 * Usado pelo StockController ao retornar os dados do estoque criado.
 */
public record StockSavedResponse(

        // Identificador único do estoque
        @JsonProperty("id")
        UUID id,

        // Quantidade de itens no estoque
        @JsonProperty("amount")
        Long amount,

        // Preço de compra do produto
        @JsonProperty("boughtPrice")
        BigDecimal boughtPrice,

        // Preço de venda do produto
        @JsonProperty("soldPrice")
        BigDecimal soldPrice,

        // Status do estoque (por exemplo, ATIVO ou INATIVO)
        @JsonProperty("status")
        StockStatus status,

        // UUID do produto associado ao estoque
        @JsonProperty("productId")
        UUID productId,

        // Nome do produto associado ao estoque
        @JsonProperty("productName")
        String productName

) {}
