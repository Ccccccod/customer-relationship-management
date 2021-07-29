/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.ProductDto;
import capstone.entity.Product;
import capstone.repository.ProductRepository;

/**
 * ProductControllerTest
 * @author DELL
 *
 */
public class ProductControllerTest
		extends AbstractDtoEntityControllerTest<ProductDto, Product, ProductRepository, ProductController, Long> {

	@Override
	protected String url() {
		return "/api/product";
	}

	@Override
	protected List<Product> resources() {
		return Arrays.asList(
				Product.builder().name("quan vai").code("HH1").build(),
				Product.builder().name("quan bo").code("HH2").build(),
				Product.builder().code("HH3").name("qua dep").build());
	}

	@Override
	protected Product resource() {
		return Product.builder().id(1L).name("kien").code("HH4").build();
	}

	@Override
	protected ProductDto createResource() {
		return ProductDto.builder()
				.name(resource().getName())
				.code(resource().getCode())
				.build();
	}

}
