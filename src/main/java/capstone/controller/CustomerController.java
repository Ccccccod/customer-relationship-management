/**
 * 
 */
package capstone.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.CustomerDto;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.FieldRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;
import capstone.service.AbstractService;

/**
 * CustomerController
 * @author Tuna
 *
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController extends AbstractDtoEntityController<CustomerDto, Customer, CustomerRepository, Long> implements IReadNameController<Customer, CustomerRepository, Long> {
	
	@Autowired
	public CustomerRepository customerRepository;

	@Autowired
	private SourceRepository sourceRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Autowired 
	private FieldRepository fieldRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CareerRepository careerRepository;

	@Override
	protected Customer dtoToEntity(CustomerDto dto, Customer customer) throws ResourceNotFoundException {
		Set<Field> fields = AbstractService.findEntitiesByIds(fieldRepository, dto.getFieldIds(), Field.class);
		Set<Career> careers = AbstractService.findEntitiesByIds(careerRepository, dto.getCareerIds(), Career.class)
				.stream().filter(career -> fields.contains(career.getField())).collect(Collectors.toSet());
		return customer.toBuilder()
				.id(dto.getId())
				.code(dto.getCode())
				.name(dto.getName())
				.shortName(dto.getShortName())
				.taxCode(dto.getTaxCode())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.address(dto.getAddress())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.classifications(AbstractService.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.fields(fields)
				.type(AbstractService.findEntityById(typeRepository, dto.getTypeId(), Type.class))
				.careers(careers)
				.build();
	}

	@Override
	protected Class<Customer> entityClass() {
		return Customer.class;
	}

}
