/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.dto.request.CustomerDto;
import capstone.entity.Customer;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CustomerRepository;
import capstone.service.iservice.INamedService;

/**
 * Customer Service
 * @author Tuna
 */
@Service
public class CustomerService extends AbstractService<CustomerDto, CustomerDto, Customer, Customer, CustomerRepository, Long>
		implements INamedService<Customer, CustomerRepository, Long> {

//	@Autowired
//	private SourceRepository sourceRepository;
//	
//	@Autowired
//	private ClassificationRepository classificationRepository;
//	
//	@Autowired 
//	private FieldRepository fieldRepository;
//	
//	@Autowired
//	private TypeRepository typeRepository;
//	
//	@Autowired
//	private CareerRepository careerRepository;
//	
//	@Autowired
//	private IncomeRepository incomeRepository;

	@Override
	protected Class<Customer> entityClass() {
		return Customer.class;
	}

	@Override
	protected Customer entityToResponse(Customer entity) {
		return entity;
	}

	@Override
	protected Customer createDtoToEntity(CustomerDto dto, Customer entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(dto.getId())
				.code(dto.getCode())
				.shortName(dto.getShortName())
				.name(dto.getName())
				.taxCode(dto.getTaxCode())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.source(sourceService.getEntityById(dto.getSourceId()))
				.classifications(classificationService.getEntitiesById(dto.getClassificationIds()))
				.fields(fieldService.getEntitiesById(dto.getFieldIds()))
				.type(typeService.getEntityById(dto.getTypeId()))
				.careers(careerService.getEntitiesById(dto.getCareerIds()))
				.address(dto.getAddress())
				.bankAccount(dto.getBankAccount())
				.bank(dto.getBank())
				.foundedDate(dto.getFoundedDate())
				.customerSince(dto.getCustomerSince())
				.income(incomeService.getEntityById(dto.getIncomeId()))
				.website(dto.getWebsite())
				.build();
	}

	@Override
	protected Customer updateDtoToEntity(CustomerDto updateDto, Customer entity) throws ResourceNotFoundException {
		return createDtoToEntity(updateDto, entity);
	}

}
