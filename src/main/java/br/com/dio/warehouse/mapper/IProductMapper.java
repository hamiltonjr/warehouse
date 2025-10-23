package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * Mapper para conversão entre entidades de produto, DTOs e requests/responses.
 * Utiliza MapStruct para gerar a implementação automaticamente.
 */
@Mapper(componentModel = SPRING)
public interface IProductMapper {

    /**
     * Converte um ProductSaveRequest em ProductEntity.
     * Ignora o ID e a lista de estoques, pois são gerados internamente.
     *
     * @param request request com dados do produto.
     * @return entidade ProductEntity pronta para persistência.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    ProductEntity toEntity(ProductSaveRequest request);

    /**
     * Converte uma entidade ProductEntity em ProductSavedResponse,
     * que será retornada ao cliente após a criação do produto.
     *
     * @param entity entidade do produto.
     * @return response com ID e nome do produto salvo.
     */
    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    /**
     * Converte uma entidade ProductEntity em DTO para envio ao storefront.
     *
     * @param entity entidade do produto.
     * @return DTO para comunicação com o microsserviço storefront.
     */
    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    /**
     * Converte uma entidade ProductEntity em ProductDetailResponse,
     * usado para retornar detalhes do produto via API.
     *
     * @param entity entidade do produto.
     * @return response contendo ID, nome e preço do produto.
     */
    ProductDetailResponse toDetailResponse(final ProductEntity entity);
}
