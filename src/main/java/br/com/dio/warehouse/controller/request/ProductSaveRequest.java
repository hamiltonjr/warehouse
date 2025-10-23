package br.com.dio.warehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO de requisição para criação de um produto no Warehouse.
 * Usado pelo ProductController ao receber dados via POST.
 */
public record ProductSaveRequest(

        // Nome do produto. Será recebido no corpo da requisição JSON.
        @JsonProperty("name")
        String name

) {}
