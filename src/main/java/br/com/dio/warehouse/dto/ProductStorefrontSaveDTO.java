package br.com.dio.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * DTO usado para enviar informações do produto para o storefront.
 * Contém dados essenciais do produto após ser salvo no warehouse.
 */
public record ProductStorefrontSaveDTO(

        // Identificador único do produto
        @JsonProperty("id")
        UUID id,

        // Nome do produto
        @JsonProperty("name")
        String name,

        // Indica se o produto está ativo ou não
        @JsonProperty("active")
        Boolean active

) {}
