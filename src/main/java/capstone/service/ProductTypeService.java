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
import capstone.dto.response.ProductTypeTreeResponse;
import capstone.entity.ProductType;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProductTypeRepository;
import capstone.service.iservice.INamedService;

/**
 * ProductTypeService
 * @author tuna
 */
@Service
public class ProductTypeService
		extends CodedService<ProductTypeDto, ProductTypeDto, ProductTypeTreeResponse, ProductType, ProductTypeRepository, Long>
		implements INamedService<ProductType, ProductTypeRepository, Long> {

	@Autowired
	protected ProductTypeRepository productTypeRepository;

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
	public Set<ProductTypeTreeResponse> getTree() {
		return productTypeRepository.findByProductType(null).stream()
				.map(productTypeToProductTypeTreeDto())
				.collect(Collectors.toSet());
	}
	
	private Function<ProductType, ProductTypeTreeResponse> productTypeToProductTypeTreeDto() {
		return pt -> {
			if (Objects.isNull(pt))
				return null;
			return ProductTypeTreeResponse.builder()
					.id(pt.getId())
					.code(pt.getCode())
					.name(pt.getName())
					.productTypeTreeDtos(productTypeSetToProductTypeTreeDtoSet().apply(pt.getProductTypes()))
					.build();
		};
	}
	
	private Function<Set<ProductType>, Set<ProductTypeTreeResponse>> productTypeSetToProductTypeTreeDtoSet() {
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
	protected ProductTypeTreeResponse entityToResponse(ProductType entity) {
		return productTypeToProductTypeTreeDto().apply(entity);
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
