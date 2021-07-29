/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.CustomerDto;
import capstone.entity.Customer;
import capstone.repository.CustomerRepository;

/**
 * CustomerControllerTest
 * @author Tuna
 *
 */

class CustomerControllerTest
		extends AbstractDtoEntityControllerTest<CustomerDto, Customer, CustomerRepository, CustomerController, Long> {

	@Override
	protected String url() {
		return "/api/customer";
	}

	@Override
	protected List<Customer> resources() {
		return Arrays.asList(resource());
	}

	@Override
	protected Customer resource() {
		return Customer.builder().code("KH111").name("zxvcxvcxv").shortName("Eurovxcvxcdoor")
				.id(1L)
				.taxCode("1234455").phone("1222323223").email("xxxyyyzzz@gmail.com")
//				.source(source1)
//				.classifications(Stream.of(classification1, classification2).collect(Collectors.toSet()))
//				.fields(Stream.of(field1, field2).collect(Collectors.toSet()))
//				.type(type1)
//				.careers(Stream.of(career1, career2).collect(Collectors.toSet()))
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam").build();
	}

	@Override
	protected CustomerDto createResource() {
		return CustomerDto.builder()
				.code(resource().getCode())
				.name(resource().getName())
				.shortName(resource().getShortName())
				.taxCode(resource().getTaxCode())
				.phone(resource().getPhone())
				.email(resource().getEmail())
				.address(resource().getAddress())
				.build();
	}

}
