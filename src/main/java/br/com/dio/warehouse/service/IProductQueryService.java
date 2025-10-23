package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.ProductEntity;
import java.util.UUID;

/**
 * Serviço de consulta de produtos.
 * Responsável por buscar produtos no banco de dados.
 * Separar responsabilidades (Query vs Command) ajuda a manter o código mais limpo
 * e respeita o princípio do SOLID (Single Responsibility Principle).
 */
public interface IProductQueryService {

    /**
     * Busca um produto pelo seu ID.
     *
     * @param id ID do produto a ser buscado
     * @return Produto encontrado
     * @throws java.util.NoSuchElementException se o produto não existir
     */
    ProductEntity findById(final UUID id);
}
