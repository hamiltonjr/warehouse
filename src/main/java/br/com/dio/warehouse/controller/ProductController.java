package br.com.dio.warehouse.controller;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controller para gerenciar produtos do Warehouse.
 * Segrega operações de escrita (IProductService) e leitura (IProductQueryService).
 */
@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;           // Serviços de escrita (save, purchase)
    private final IProductQueryService queryService; // Serviços de leitura (findById)
    private final IProductMapper mapper;             // Mapper para conversão de DTOs

    /**
     * Cria um novo produto.
     * @param request dados do produto a serem salvos
     * @return DTO do produto salvo
     */
    @PostMapping
    @ResponseStatus(CREATED)
    ProductSavedResponse create(@RequestBody final ProductSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);                 // chama serviço de escrita
        return mapper.toSavedResponse(entity);        // converte para response
    }

    /**
     * Marca um produto como comprado (simula movimentação de estoque).
     * @param id UUID do produto
     */
    @PostMapping("{id}/purchase")
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable final UUID id) {
        service.purchase(id);                          // delega ao serviço de escrita
    }

    /**
     * Retorna os detalhes de um produto específico.
     * @param id UUID do produto
     * @return DTO com detalhes do produto
     */
    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable final UUID id) {
        var dto = queryService.findById(id);          // chama serviço de leitura
        return mapper.toDetailResponse(dto);
    }
}
