/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import capstone.BackEndCapstoneApplication;
import capstone.dto.request.CustomerDto;
import capstone.entity.Customer;
import capstone.repository.CustomerRepository;

/**
 * CustomerControllerTest
 * @author Tuna
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndCapstoneApplication.class)
class CustomerControllerTest
		extends AbstractDtoEntityControllerTest<CustomerDto, Customer, CustomerRepository, CustomerController, Long> {

	@Override
	protected String url() {
		return "/api/customer";
	}

	@Override
	protected List<Customer> resources() {
		return Arrays.asList(resource);
	}

	@Override
	protected Customer resource() {
		return Customer.builder().code("KH00001").name("Công ty TNHH Eurodoor").shortName("Eurodoor")
				.taxCode("0185514943").phone("0185514943").email("letan@eurodoor.com.vn")
//				.source(source1)
//				.classifications(Stream.of(classification1, classification2).collect(Collectors.toSet()))
//				.fields(Stream.of(field1, field2).collect(Collectors.toSet()))
//				.type(type1)
//				.careers(Stream.of(career1, career2).collect(Collectors.toSet()))
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam").build();
	}

}
