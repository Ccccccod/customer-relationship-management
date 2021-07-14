/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.ProductDto;
import capstone.entity.Product;
import capstone.entity.ProductType;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProductTypeRepository;

/**
 * @author Tuna
 *
 */
@Service
public class ProductService extends AbstractService implements IDtoToEntityService<ProductDto, Product, Long>{
	
	@Autowired
	ProductTypeRepository productTypeRepository;

	@Override
	public Product dtoToEntity(ProductDto dto) throws ResourceNotFoundException {
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

}
