/**
 * 
 */
package capstone.service;

import java.io.Serializable;
import java.util.LinkedHashSet;
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
 * @author tuna
 */
@Service
public class ProductInfoService {
	
	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Generate {@link ProductInfo} from {@link Product}
	 * @param product
	 * @return
	 */
	public ProductInfo generateFromProduct(Product product) {
		return ProductInfo.builder()
				.product(product)
//				.productCode(product.getCode())
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
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + productId, ProductInfo.class));
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
	
	public ProductInfo generateFromProductInfoDto(ProductInfoDto d) throws ResourceNotFoundException {
		Product product = productService.getEntityById(d.getProductId());
		return ProductInfo.builder()
				.id(d.getId())
				.product(productService.getEntityById(d.getProductId()))
				.productCode(product.getCode())
				.explanation(d.getExplanation())
//				.unit(unitService.getEntityById(d.getUnitId()))
				.unit(product.getUnit())
				.amount(d.getAmount())
				.price(d.getPrice())
				.discount(d.getDiscount())
				.vat(d.getVat())
				.build();
	}
	
	public Set<ProductInfo> generateFromProductInfoDto(Set<ProductInfoDto> productInfoDtos) throws ResourceNotFoundException {
		if (Objects.isNull(productInfoDtos))
			return null;
		Set<ProductInfo> set = new LinkedHashSet<ProductInfo>();
		for (ProductInfoDto productInfoDto : productInfoDtos) {
			set.add(generateFromProductInfoDto(productInfoDto));
		}
		return set;
	}

	public <T extends ProductInfoed, ID extends Serializable> ProductInfo create(ID productInfoedId,
			JpaRepository<T, ID> repository, Class<T> class1, Long productId) throws ResourceNotFoundException {
		T t = repository.findById(productInfoedId).orElseThrow(
				() -> new ResourceNotFoundException(class1.getName() + " not found for this id: " + productInfoedId, class1));
		ProductInfo productInfo = this.generateFromProduct(productId);
		t.addToProductInfo(productInfo);
		repository.saveAndFlush(t);
		return productInfo;
	}

}
