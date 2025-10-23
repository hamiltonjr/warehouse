package br.com.dio.warehouse.entity;

/**
 * Enum que representa os possíveis status de um estoque.
 * Usar enum aumenta legibilidade e evita erros de "código mágico" como em COBOL.
 */
public enum StockStatus {
    IN_CONFERENCE,
    AVAILABLE,
    UNAVAILABLE
}
