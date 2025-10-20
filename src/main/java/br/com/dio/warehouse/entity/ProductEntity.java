package br.com.dio.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

import static br.com.dio.warehouse.entity.StockStatus.AVAILABLE;
import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {
    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public StockEntity decStock() {
        var stock = this.stocks.stream()
                .filter(s -> s.getStatus().equals(AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
        stock.decAmount();
        return stock;
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
