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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ProductTypeDto;
import capstone.dto.response.ProductTypeTreeResponse;
import capstone.entity.ProductType;
import capstone.exception.ErrorDetails;
import capstone.repository.ProductTypeRepository;
import capstone.service.ProductTypeService;

/**
 * Product Type Controller
 * Loại hàng hóa Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/productType")
public class ProductTypeController extends
		CRUDController<ProductTypeDto, ProductTypeDto, ProductType, ProductType, ProductTypeRepository, ProductTypeService, Long>
		implements IReadNameController<ProductType, ProductTypeService, Long> {
	
	@Autowired
	protected ProductTypeService productTypeService;
	
	/**
	 * Get a {@link List} of available {@link ProductType} to insert or update
	 * {@link ProductType} field of a {@link ProductType}
	 * @param id id of {@link ProductType} that's being inserted or updated
	 * @return
	 */
	@GetMapping("/getavailable/{id}")
	public ResponseEntity<?> getAll(@PathVariable Long id) {
		if (Objects.isNull(id)) 
			return ResponseEntity.badRequest().body(new ErrorDetails("id must not be null"));
		List<ProductType> productTypes = this.productTypeService.getAvailableProductTypesForAProductType(id);
		return ResponseEntity.ok(productTypes);
	}
	
	@GetMapping("/tree")
	public ResponseEntity<Set<ProductTypeTreeResponse>> getTree() {
		Set<ProductTypeTreeResponse> productTypeTreeDtos = this.productTypeService.getTree();
		return ResponseEntity.ok(productTypeTreeDtos);
	}

	@Autowired
	private ProductTypeService productTypeService2;
	
	@Override
	public ProductTypeService getService() {
		return productTypeService2;
	}

}
