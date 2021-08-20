/**
 * 
 */
package capstone.service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import capstone.dto.request.ProductInfoDto;
import capstone.entity.Product;
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
import capstone.model.ProductInfoed;
import capstone.repository.ProductInfoRepository;
import capstone.repository.ProductRepository;

/**
 * ProductInfoService
 * @author Tuna
 *
 */
@Service
public class ProductInfoService {
	
	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	/**
	 * Generate {@link ProductInfo} from {@link Product}
	 * @param product
	 * @return
	 */
	public ProductInfo generateFromProduct(Product product) {
		return ProductInfo.builder()
				.product(product)
				.productCode(product.getCode())
				.explanation(product.getExplanation())
				.unit(product.getUnit())
				.amount(1)
				.price(product.getSellPrice())
				.vat(product.getVat())
				.discount(0)
				.build();
	};
	
	/**
	 * Generate {@link ProductInfo} from {@link Product}'s id
	 * @param productId
	 * @return Generated {@link ProductInfo}
	 * @throws ResourceNotFoundException if no {@link Product} are fouund by productId
	 */
	public ProductInfo generateFromProduct(Long productId) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + productId));
		return this.generateFromProduct(product);
	};
	
	public List<ProductInfo> generatetFromProducts(List<Product> products) {
		if (Objects.isNull(products))
			return null;
		return products.stream().map(this::generateFromProduct).collect(Collectors.toList());
	}
	
	public Set<ProductInfo> generatetFromProducts(Set<Product> products) {
		if (Objects.isNull(products))
			return null;
		return products.stream().map(this::generateFromProduct).collect(Collectors.toSet());
	}
	
	public ProductInfo generateFromProductInfoDto(ProductInfoDto dto) {
		return ProductInfo.builder()
				.productCode(dto.getProductCode())
				.explanation(dto.getExplanation())
				.unit(dto.getUnit())
				.amount(dto.getAmount())
				.price(dto.getPrice())
				.discount(dto.getDiscount())
				.vat(dto.getVat())
				.build();
	}
	
	public Set<ProductInfo> generateFromProductInfoDto(Set<ProductInfoDto> productInfoDtos) {
		if (Objects.isNull(productInfoDtos))
			return null;
		return productInfoDtos.stream().map(this::generateFromProductInfoDto).collect(Collectors.toSet());
	}

	public <T extends ProductInfoed, ID extends Serializable> ProductInfo create(ID productInfoedId,
			JpaRepository<T, ID> repository, Class<T> class1, Long productId) throws ResourceNotFoundException {
		T t = repository.findById(productInfoedId).orElseThrow(
				() -> new ResourceNotFoundException(class1.getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = this.generateFromProduct(productId);
		t.addToProductInfo(productInfo);
		repository.saveAndFlush(t);
		return productInfo;
	}

}
