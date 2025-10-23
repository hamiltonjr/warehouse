package br.com.dio.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.*;
import static br.com.dio.warehouse.entity.StockStatus.AVAILABLE;
import static jakarta.persistence.CascadeType.ALL;

/**
 * Entidade que representa um produto no warehouse.
 * Mantém informações básicas do produto e seu conjunto de estoques.
 */
@Entity
@Getter
@Setter
@ToString
public class ProductEntity {

    /** Identificador único do produto */
    @Id
    private UUID id;

    /** Nome do produto */
    private String name;

    /** Conjunto de estoques associados ao produto */
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();

    /**
     * Equals baseado em id e nome do produto.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    /**
     * HashCode baseado em id e nome do produto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Retorna o estoque com menor preço de venda entre os disponíveis.
     * @return StockEntity com menor soldPrice
     */
    private StockEntity getStockWithMinSoldPrice() {
        return this.stocks.stream()
                .filter(s -> s.getStatus().equals(AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
    }

    /**
     * Decrementa a quantidade do estoque com menor preço disponível.
     * @return StockEntity alterado
     */
    public StockEntity decStock() {
        var stock = getStockWithMinSoldPrice();
        stock.decAmount();
        return stock;
    }

    /**
     * Retorna o preço do produto baseado no estoque com menor preço de venda disponível.
     * @return BigDecimal do preço
     */
    public BigDecimal getPrice() {
        return getStockWithMinSoldPrice().getSoldPrice();
    }

    /**
     * Indica se o produto está indisponível.
     * @return false por padrão (implementação pode ser ajustada)
     */
    public boolean isUnavailable() {
        return false;
    }

    /**
     * Garante que o id seja gerado antes da persistência.
     */
    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
