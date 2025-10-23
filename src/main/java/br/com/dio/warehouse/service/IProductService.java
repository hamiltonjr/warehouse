package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.ProductEntity;
import java.util.UUID;

/**
 * Interface de serviço responsável pelas operações básicas de produtos.
 * Define o contrato que qualquer implementação deve cumprir.
 */
public interface IProductService {

    /**
     * Salva um novo produto ou atualiza um existente.
     * @param entity produto a ser salvo
     * @return a entidade persistida
     */
    ProductEntity save(final ProductEntity entity);

    /**
     * Realiza a compra de um produto, diminuindo seu estoque no warehouse.
     * @param id identificador do produto
     */
    void purchase(final UUID id);
}
