package br.com.dio.warehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de resposta para detalhar um produto.
 * Usado pelo ProductController ao retornar informações de um produto.
 */
public record ProductDetailResponse(

        // Identificador único do produto
        @JsonProperty("id")
        UUID id,

        // Nome do produto
        @JsonProperty("name")
        String name,

        // Preço atual do produto
        @JsonProperty("price")
        BigDecimal price

) {}
