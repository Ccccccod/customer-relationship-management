/**
 * 
 */
package capstone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.CustomerDto;
import capstone.entity.Customer;
import capstone.exception.ErrorDetails;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CustomerRepository;
import capstone.service.CustomerService;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerRepository getRepository() {
		return this.customerRepository;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAll(){
		return ResponseEntity.ok(getRepository().findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) throws ResourceNotFoundException {
		return ResponseEntity.ok(getRepository().findById(id) //
				.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id)));
	}
	
	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody CustomerDto dto) throws ResourceNotFoundException {
		if (!dto.isNew() && getRepository().existsById(dto.getId())) {
			return ResponseEntity.badRequest().body(new ErrorDetails("An entity is already exist with id: " + dto.getId()));
		}
		Customer customer = customerService.dtoToEntity(dto);
		return ResponseEntity.ok(getRepository().save(customer));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> create(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerDto dto) throws ResourceNotFoundException {
		dto.setId(id);
		return create(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerDto dto) throws ResourceNotFoundException {
		if (!getRepository().existsById(id)) {
			DtoUtils.throwResourceNotFoundException(id);
		}
		dto.setId(id);
		Customer customer = customerService.dtoToEntity(dto);
		return ResponseEntity.ok(getRepository().save(customer));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") List<Long> ids) throws ResourceNotFoundException {
		List<Customer> deletedTS = getRepository().findAllById(ids);
		getRepository().deleteAll(deletedTS);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedTS));
	}
	
}
