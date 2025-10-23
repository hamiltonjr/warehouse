package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.StockSaveRequest;
import br.com.dio.warehouse.controller.response.StockSavedResponse;
import br.com.dio.warehouse.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * Mapper para conversão entre entidades de estoque, DTOs e requests/responses.
 * Utiliza MapStruct para gerar a implementação automaticamente.
 */
@Mapper(componentModel = SPRING)
public interface IStockMapper {

    /**
     * Converte um StockSaveRequest em StockEntity.
     * - Ignora o ID, pois será gerado automaticamente.
     * - Mapeia o productId do request para o ProductEntity associado.
     * - Define o status inicial como IN_CONFERENCE.
     *
     * @param request request com dados do estoque.
     * @return entidade StockEntity pronta para persistência.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "status", expression = "java(br.com.dio.warehouse.entity.StockStatus.IN_CONFERENCE)")
    StockEntity toEntity(final StockSaveRequest request);

    /**
     * Converte uma entidade StockEntity em StockSavedResponse,
     * usado para retornar informações de estoque ao cliente.
     * - Mapeia o ID e o nome do produto.
     *
     * @param entity entidade de estoque.
     * @return response contendo detalhes do estoque salvo.
     */
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    StockSavedResponse toResponse(final StockEntity entity);
}
