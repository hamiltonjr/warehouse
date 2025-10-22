package br.com.dio.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import static br.com.dio.warehouse.entity.StockStatus.UNAVAILABLE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {
    @Id
    private UUID id;

    private long amount;

    private BigDecimal boughtPrice;

    private BigDecimal soldPrice;

    @Enumerated(STRING)
    private StockStatus status;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public boolean isUnavailable() {
        return status == UNAVAILABLE;
    }

    public void decAmount() {
        this.amount--;
        if (this.amount == 0) {
            this.status = UNAVAILABLE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StockEntity that)) return false;
        return amount == that.amount &&
                Objects.equals(id, that.id) &&
                Objects.equals(boughtPrice, that.boughtPrice) &&
                Objects.equals(soldPrice, that.soldPrice) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, boughtPrice, soldPrice, status);
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
