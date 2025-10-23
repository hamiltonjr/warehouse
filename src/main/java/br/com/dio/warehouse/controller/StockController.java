package br.com.dio.warehouse.controller;

import br.com.dio.warehouse.controller.request.StockSaveRequest;
import br.com.dio.warehouse.controller.response.StockSavedResponse;
import br.com.dio.warehouse.mapper.IStockMapper;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controller para gerenciar o estoque de produtos no Warehouse.
 * Centraliza operações de criação, liberação e inativação de estoques.
 */
@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final IStockService service;   // Serviço de estoque
    private final IStockMapper mapper;     // Mapper para conversão de DTOs/Entidades

    /**
     * Cria um novo registro de estoque.
     * @param request dados do estoque a serem salvos
     * @return DTO do estoque salvo
     */
    @PostMapping
    @ResponseStatus(CREATED)
    StockSavedResponse save(@RequestBody final StockSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);       // delega ao serviço de escrita
        return mapper.toResponse(entity);    // converte para response
    }

    /**
     * Libera um estoque específico (ex: para venda ou reserva).
     * @param id UUID do estoque
     */
    @PutMapping("{id}/release")
    @ResponseStatus(NO_CONTENT)
    void release(@PathVariable final UUID id) {
        service.release(id);                 // chama serviço de negócio
    }

    /**
     * Marca um estoque como inativo.
     * @param id UUID do estoque
     */
    @DeleteMapping("{id}/release")
    @ResponseStatus(NO_CONTENT)
    void inactive(@PathVariable final UUID id) {
        service.inactive(id);                // chama serviço de negócio
    }
}
