/**
 * 
 */
package capstone.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.ProductTypeDto;
import capstone.dto.response.ProductTypeTreeResponse;
import capstone.entity.ProductType;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.ProductTypeRepository;
import capstone.service.iservice.INamedService;

/**
 * ProductTypeService
 * @author tuna
 */
@Service
public class ProductTypeService
		extends CodedService<ProductTypeDto, ProductTypeDto, ProductType, ProductType, ProductTypeRepository, Long>
		implements INamedService<ProductType, ProductTypeRepository, Long> {

	@Autowired
	protected ProductTypeRepository productTypeRepository;
	
	@Override
	List<ProductType> getAllEntities() throws ResourceNotFoundException {
		Session session = enableDeletedFilter(false);
		try {
			return this.repository.findByProductTypeNull();
		} finally {
			disableDeletedFilter(session);
		}
	}

	public List<IdAndName<Long>> getAvailableProductTypesForAProductType(Long id) throws ResourceNotFoundException {
		assert id != null;
		Session session = null;
		try {
			session = enableDeletedFilter(false);
//			return this.productTypeRepository.findAll().stream() //
//					.filter(pt -> {
//						ProductType productType = pt;
//						do {
//							if (productType.getId().equals(id)) {
//								return false;
//							}
//						} while (!Objects.isNull(productType = productType.getProductType()));
//						return true;
//					}) //
//					.collect(Collectors.toList());
			Set<Long> notAllow = this.flat(this.getEntityById(id)).map(ProductType::getId).collect(Collectors.toSet());
			return this.getAllName().stream() //
					.filter(p -> !notAllow.contains(p.getId())) //
					.map(p -> IdAndName.newInstance(p.getId(), p.getName()))
					.collect(Collectors.toList());
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	private Stream<ProductType> flat(ProductType productType) {
		Set<ProductType> productTypes = productType.getProductTypes();
		if (productTypes != null && !productTypes.isEmpty()) {
			return productTypes.stream().flatMap(this::flat);
		} else {
			return Stream.of(productType);
		}
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
			if (Objects.isNull(pts) || pts.isEmpty())
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
