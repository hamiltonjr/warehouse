package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;
import br.com.dio.warehouse.repository.StockRepository;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import static br.com.dio.warehouse.entity.StockStatus.AVAILABLE;
import static br.com.dio.warehouse.entity.StockStatus.UNAVAILABLE;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockRepository repository;
    private final IProductQueryService productQueryService; // Para buscar produtos associados ao stock
    private final IProductChangeAvailabilityProducer producer; // Para notificar o storefront sobre mudança de status

    /**
     * Salva um novo estoque no banco de dados.
     * Antes de salvar, garante que a referência do produto está correta.
     */
    @Override
    public StockEntity save(final StockEntity entity) {
        // Atualiza a referência do produto antes de persistir
        entity.setProduct(productQueryService.findById(entity.getProduct().getId()));
        return repository.save(entity);
    }

    /**
     * Libera o estoque, tornando-o disponível para vendas.
     * Apenas delega para changeStatus() com StockStatus.AVAILABLE.
     */
    @Override
    public void release(final UUID id) {
        changeStatus(id, AVAILABLE);
    }

    /**
     * Marca o estoque como inativo, tornando-o indisponível.
     * Apenas delega para changeStatus() com StockStatus.UNAVAILABLE.
     */
    @Override
    public void inactive(final UUID id) {
        changeStatus(id, UNAVAILABLE);
    }

    /**
     * Altera o status do estoque e notifica o storefront sobre a mudança.
     * Este método centraliza toda a lógica de alteração de status.
     */
    @Override
    public void changeStatus(final UUID id, final StockStatus status) {
        // Busca o estoque pelo ID, lança exceção se não existir
        var entity = repository.findById(id).orElseThrow();

        // Atualiza o status do estoque
        entity.setStatus(status);

        // Persiste a alteração no banco
        repository.save(entity);

        // Notifica o storefront sobre a alteração de status do produto
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }
}
