/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.CustomerDto;
import capstone.entity.Customer;
import capstone.repository.CustomerRepository;
import capstone.service.CustomerService;

/**
 * CustomerController
 * Khách hàng Controller
 * @author Tuna
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController
		extends CRUDController<CustomerDto, CustomerDto, Customer, Customer, CustomerRepository, CustomerService, Long>
		implements IReadNameController<Customer, CustomerService, Long> {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerService getService() {
		return customerService;
	}

}
