package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.service.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements IProductQueryService {

    private final ProductRepository repository;

    /**
     * Busca o produto pelo ID, lançando exceção se não existir.
     * Este é o método que substitui o antigo findById privado do ProductServiceImpl.
     */
    @Override
    public ProductEntity findById(final UUID id) {
        return repository.findById(id)
                .orElseThrow(); // Pode customizar a exceção se quiser
    }
}
