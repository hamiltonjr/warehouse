package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;
import java.util.UUID;

/**
 * Interface de serviço responsável pelas operações de estoque.
 * Define o contrato que qualquer implementação deve cumprir.
 */
public interface IStockService {

    /**
     * Salva um novo estoque ou atualiza um existente.
     * @param entity estoque a ser salvo
     * @return a entidade persistida
     */
    StockEntity save(final StockEntity entity);

    /**
     * Libera um estoque específico, atualizando seu status conforme necessário.
     * @param id identificador do estoque
     */
    void release(final UUID id);

    /**
     * Inativa um estoque específico, tornando-o indisponível.
     * @param id identificador do estoque
     */
    void inactive(final UUID id);

    /**
     * Altera o status de um estoque específico.
     * @param id identificador do estoque
     * @param status novo status a ser aplicado
     */
    void changeStatus(final UUID id, final StockStatus status);
}
