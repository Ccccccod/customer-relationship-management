/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Product;
import capstone.entity.ProductInfo;

/**
 * ProductInfoService
 * @author Tuna
 *
 */
@Service
public class ProductInfoService {
	
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

}
