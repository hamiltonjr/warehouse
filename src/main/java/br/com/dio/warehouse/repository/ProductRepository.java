package br.com.dio.warehouse.repository;

import br.com.dio.warehouse.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Interface de repositório para a entidade ProductEntity.
 *
 * Extendendo JpaRepository, esta interface já herda métodos comuns para persistência,
 * como save(), findById(), findAll(), deleteById() etc.
 *
 * <UUID> indica que o ID da entidade é do tipo UUID.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    // Aqui você pode adicionar consultas personalizadas, por exemplo:
    // List<ProductEntity> findByNameContaining(String name);
    // Mas por enquanto não há métodos adicionais.
}
