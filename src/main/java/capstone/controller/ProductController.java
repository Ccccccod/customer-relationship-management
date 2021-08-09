/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ProductDto;
import capstone.entity.Product;
import capstone.entity.ProductType;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProductRepository;
import capstone.repository.ProductTypeRepository;
import capstone.service.AbstractService;

/**
 * Product controller
 * Hàng hóa Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractDtoEntityController<ProductDto, Product, ProductRepository, Long>
		implements IReadNameController<Product, ProductRepository, Long> {
	
	@Autowired
	ProductTypeRepository productTypeRepository;

	@Override
	protected Product dtoToEntity(ProductDto dto) throws ResourceNotFoundException {
		return Product.builder()
				.id(dto.getId())
				.name(dto.getName())
				.code(dto.getCode())
				.productType(AbstractService.findEntityById(productTypeRepository, dto.getProductTypeId(), ProductType.class))
				.explanation(dto.getExplanation())
				.unit(dto.getUnit())
				.sellPrice(dto.getSellPrice())
				.sellPrice1(dto.getSellPrice1())
				.sellPrice2(dto.getSellPrice2())
				.permanentPrice(dto.getPermanentPrice())
				.buyPrice(dto.getBuyPrice())
				.enterUnitPriorityAfterTax(dto.getEnterUnitPriorityAfterTax())
				.vat(dto.getVat())
				.implicitRecord(dto.getImplicitRecord())
				.costUnitPrice(dto.getCostUnitPrice())
				.build();
	}

	@Override
	protected Class<Product> entityClass() {
		return Product.class;
	}

}
