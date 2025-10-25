package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;          // Repositório JPA
    private final IProductQueryService queryService;     // Serviço de consulta separado
    private final IStockService stockService;           // Serviço de estoque
    private final RestClient storefrontClient;          // Client para enviar dados ao storefront
    private final IProductMapper mapper;                // Mapper MapStruct

    /**
     * Salva o produto no banco e envia para o storefront.
     */
    @Override
    public ProductEntity save(final ProductEntity entity) {
        repository.save(entity);                       // Salva no DB
        var dto = mapper.toDTO(entity);                // Converte para DTO
        saveStorefront(dto);                           // Envia para o storefront
        return entity;
    }

    /**
     * Compra o produto, decrementando o estoque.
     * Caso o estoque fique indisponível, notifica o status.
     */
    @Override
    public void purchase(final UUID id) {
        var entity = queryService.findById(id);       // Usa o serviço de consulta
        var stock = entity.decStock();                // Decrementa estoque
        repository.save(entity);                      // Salva atualização
        if (stock.isUnavailable()) {                  // Se acabar estoque, notifica
            stockService.changeStatus(stock.getId(), stock.getStatus());
        }
    }

    /**
     * Método privado para enviar os dados ao storefront.
     */
    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSaveDTO.class);
    }
}
