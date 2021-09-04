/**
 * 
 */
package capstone.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.ProductTypeDto;
import capstone.dto.response.ProductTypeTreeDto;
import capstone.entity.ProductType;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProductTypeRepository;

/**
 * ProductTypeService
 * @author tuna
 */
@Service
public class ProductTypeService extends AbstractService<ProductTypeDto, ProductTypeDto, ProductType, ProductType, ProductTypeRepository, Long> implements IDtoToEntityService<ProductTypeDto, ProductType, Long>{

	@Autowired
	protected ProductTypeRepository productTypeRepository;

	@Override
	public ProductType dtoToEntity(ProductTypeDto dto) throws ResourceNotFoundException {
		ProductType productType = ProductType.builder()
				.id(dto.getId())
				.code(dto.getCode())
				.name(dto.getName())
				.productType(findEntityById(productTypeRepository, dto.getProductTypeId(), ProductType.class))
				.build();
		return productType;
	}

	public List<ProductType> getAvailableProductTypesForAProductType(Long id) {
		assert id != null;
		return this.productTypeRepository.findAll().stream() //
				.filter(pt -> {
					ProductType productType = pt;
					do {
						if (productType.getId().equals(id)) {
							return false;
						}
					} while (!Objects.isNull(productType = productType.getProductType()));
					return true;
				}) //
				.collect(Collectors.toList());
	}
	
	/**
	 * Get Tree
	 * @return
	 */
	public Set<ProductTypeTreeDto> getTree() {
		return productTypeRepository.findByProductType(null).stream()
				.map(productTypeToProductTypeTreeDto())
				.collect(Collectors.toSet());
	}
	
	private Function<ProductType, ProductTypeTreeDto> productTypeToProductTypeTreeDto() {
		return pt -> {
			if (Objects.isNull(pt))
				return null;
			return ProductTypeTreeDto.builder()
					.id(pt.getId())
					.code(pt.getCode())
					.name(pt.getName())
					.productTypeTreeDtos(productTypeSetToProductTypeTreeDtoSet().apply(pt.getProductTypes()))
					.build();
		};
	}
	
	private Function<Set<ProductType>, Set<ProductTypeTreeDto>> productTypeSetToProductTypeTreeDtoSet() {
		return pts -> {
			if (Objects.isNull(pts))
				return null;
			return pts.stream().map(this.productTypeToProductTypeTreeDto()).collect(Collectors.toSet());
		};
	}

	@Override
	protected Class<ProductType> entityClass() {
		return ProductType.class;
	}

	@Override
	protected ProductType entityToResponse(ProductType entity) {
		return entity;
	}

	@Override
	protected ProductType createDtoToEntity(ProductTypeDto dto, ProductType entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(dto.getId())
				.code(dto.getCode())
				.name(dto.getName())
				.productType(productTypeService.getEntityById(dto.getProductTypeId()))
				.build();
	}

	@Override
	protected ProductType updateDtoToEntity(ProductTypeDto updateDto, ProductType entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
