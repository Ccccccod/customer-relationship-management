package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.ProductTypeDto;
import capstone.entity.ProductType;
import capstone.repository.ProductTypeRepository;

/**
 * ProductTypeControllerTest
 * @author Tuna
 *
 */
public class ProductTypeControllerTest extends
		AbstractDtoEntityControllerTest<ProductTypeDto, ProductType, ProductTypeRepository, ProductTypeController, Long> {

	@Override
	protected String url() {
		return "/api/productType";
	}

	@Override
	protected List<ProductType> resources() {
		return Arrays.asList(ProductType.builder()
				.name("ao vai")
				.code("LH1")
				.build() ,
				ProductType.builder()
						.name("ao bo")
						.code("LH2")
						.build() ,
						ProductType.builder()
						.code("LH3")
								.name("ao dep")
								.build());
	}

	@Override
	protected ProductType resource() {
		return ProductType.builder().id(1L).name("ao coc").code("LH4").build();
	}

	@Override
	protected ProductTypeDto createResource() {
		return ProductTypeDto.builder()
				.name(resource().getName())
				.code(resource().getCode())
				.build();
	}

}
