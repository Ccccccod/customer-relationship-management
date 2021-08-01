/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.entity.Product;
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
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
	
	/**
	 * Generate {@link ProductInfo} from {@link Product}
	 * @param product
	 * @return
	 */
	public ProductInfo generateFromProduct(Product product) {
		return ProductInfo.builder()
				.productCode(product.getCode())
				.explanation(product.getExplanation())
				.unit(product.getUnit())
				.amount(1)
				.price(product.getSellPrice())
				.vat(product.getVat())
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

}
