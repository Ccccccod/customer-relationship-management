/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ProductTypeDto;
import capstone.dto.response.ProductTypeTreeDto;
import capstone.entity.ProductType;
import capstone.exception.ErrorDetails;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProductTypeRepository;
import capstone.service.AbstractService;
import capstone.service.ProductTypeService;

/**
 * Product Type Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/productType")
public class ProductTypeController
		extends AbstractDtoEntityController<ProductTypeDto, ProductType, ProductTypeRepository, Long>
		implements IReadNameController<ProductType, ProductTypeRepository, Long> {
	
	@Autowired
	protected ProductTypeService productTypeService;
	
	/**
	 * Get a {@link List} of available {@link ProductType} to insert or update
	 * {@link ProductType} field of a {@link ProductType}
	 * @param id id of {@link ProductType} that's being inserted or updated
	 * @return
	 */
	@GetMapping("/getavailable/{id}")
	public ResponseEntity<?> getAll(Long id) {
		if (Objects.isNull(id)) 
			return ResponseEntity.badRequest().body(new ErrorDetails("id must not be null"));
		List<ProductType> productTypes = this.productTypeService.getAvailableProductTypesForAProductType(id);
		return ResponseEntity.ok(productTypes);
	}
	
	@GetMapping("/tree")
	public ResponseEntity<Set<ProductTypeTreeDto>> getTree() {
		Set<ProductTypeTreeDto> productTypeTreeDtos = this.productTypeService.getTree();
		return ResponseEntity.ok(productTypeTreeDtos);
	}

	@Override
	protected ProductType dtoToEntity(ProductTypeDto dto) throws ResourceNotFoundException {
		return ProductType.builder()
				.id(dto.getId())
				.code(dto.getCode())
				.name(dto.getName())
				.productType(AbstractService.findEntityById(this.repository, dto.getProductTypeId(), ProductType.class))
				.build();
	}
	
	

}
