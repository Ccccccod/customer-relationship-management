/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ProductDto;
import capstone.entity.Product;
import capstone.repository.ProductRepository;
import capstone.service.ProductService;

/**
 * Product controller
 * Hàng hóa Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/product")
public class ProductController extends CRUDController<ProductDto, ProductDto, Product, Product, ProductRepository, ProductService, Long>
		implements IReadNameController<Product, ProductService, Long> {

	@Autowired
	private ProductService productService;
	
	@Override
	public ProductService getService() {
		return productService;
	}

}
