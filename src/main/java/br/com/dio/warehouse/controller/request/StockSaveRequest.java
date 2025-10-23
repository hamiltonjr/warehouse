package br.com.dio.warehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de requisição para criação de um registro de estoque.
 * Usado pelo StockController ao receber dados via POST.
 */
public record StockSaveRequest(

        // Quantidade de itens em estoque
        @JsonProperty("amount")
        Long amount,

        // Preço de compra do produto
        @JsonProperty("boughtPrice")
        BigDecimal boughtPrice,

        // Preço de venda do produto
        @JsonProperty("soldPrice")
        BigDecimal soldPrice,

        // UUID do produto relacionado a este estoque
        @JsonProperty("productId")
        UUID productId

) {}
