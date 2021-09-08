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
import capstone.repository.ProductRepository;
import capstone.repository.ProductTypeRepository;
import capstone.service.iservice.INamedService;

/**
 * ProductService
 * @author tuna
 */
@Service
public class ProductService extends AbstractService<ProductDto, ProductDto, Product, Product, ProductRepository, Long>
		implements IDtoToEntityService<ProductDto, Product, Long>, INamedService<Product, ProductRepository, Long> {

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
				.unit(unitService.getEntityById(dto.getUnitId()))
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

	@Override
	protected Product entityToResponse(Product entity) {
		return entity;
	}

	@Override
	protected Product createDtoToEntity(ProductDto dto, Product entity) throws ResourceNotFoundException {
		return  entity.toBuilder()
				.id(dto.getId())
				.name(dto.getName())
				.code(dto.getCode())
				.productType(productTypeService.getEntityById(dto.getProductTypeId()))
				.explanation(dto.getExplanation())
				.unit(unitService.getEntityById(dto.getUnitId()))
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
	protected Product updateDtoToEntity(ProductDto updateDto, Product entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
