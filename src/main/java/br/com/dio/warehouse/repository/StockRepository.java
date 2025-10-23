package br.com.dio.warehouse.repository;

import br.com.dio.warehouse.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Interface de repositório para a entidade StockEntity.
 *
 * Herdando JpaRepository, esta interface fornece métodos prontos para:
 * - salvar (save)
 * - buscar por id (findById)
 * - buscar todos (findAll)
 * - deletar por id (deleteById)
 * - entre outros
 *
 * <UUID> indica que a chave primária da entidade StockEntity é do tipo UUID.
 */
@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {
    // Métodos adicionais podem ser adicionados aqui conforme a necessidade,
    // por exemplo:
    // List<StockEntity> findByStatus(StockStatus status);
}
