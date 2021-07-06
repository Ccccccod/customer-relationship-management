/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ProductDto;
import capstone.entity.Product;

/**
 * Product controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractDtoEntityController<ProductDto, Product, Long> {

}
