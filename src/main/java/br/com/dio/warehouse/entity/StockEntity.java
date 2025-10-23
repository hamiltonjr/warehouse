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

    // ID gerado internamente pelo sistema antes de persistir
    @Id
    private UUID id;

    // Quantidade disponível em estoque
    private long amount;

    // Preço de compra do produto
    private BigDecimal boughtPrice;

    // Preço de venda do produto
    private BigDecimal soldPrice;

    // Status do estoque (AVAILABLE ou UNAVAILABLE)
    @Enumerated(STRING)
    private StockStatus status;

    // Relacionamento correto: muitos estoques para um produto
    @ManyToOne(fetch = FetchType.LAZY) // Lazy para não carregar o produto automaticamente
    @JoinColumn(name = "product_id", nullable = false) // FK no banco
    @ToString.Exclude // Evita recursão infinita no toString
    private ProductEntity product;

    // Verifica se o estoque está indisponível
    public boolean isUnavailable() {
        return status == UNAVAILABLE;
    }

    // Decrementa a quantidade e atualiza o status
    public void decAmount() {
        this.amount--;
        if (this.amount == 0) {
            this.status = UNAVAILABLE;
        }
    }

    // Garante que dois estoques são iguais se todos os campos importantes forem iguais
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

    // Antes de persistir, gera um UUID para o ID
    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
