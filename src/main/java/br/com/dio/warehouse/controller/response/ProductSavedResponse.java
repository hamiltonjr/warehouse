package br.com.dio.warehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * DTO de resposta para indicar que um produto foi salvo com sucesso.
 * Usado pelo ProductController ao retornar o resultado da criação de um produto.
 */
public record ProductSavedResponse(

        // Identificador único do produto criado
        @JsonProperty("id")
        UUID id,

        // Nome do produto criado
        @JsonProperty("name")
        String name

) {}
