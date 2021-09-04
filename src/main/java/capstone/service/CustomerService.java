/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.CustomerDto;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.Income;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.FieldRepository;
import capstone.repository.IncomeRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;

/**
 * Customer Service
 * @author Tuna
 */
@Service
public class CustomerService extends AbstractService<CustomerDto, CustomerDto, Customer, Customer, CustomerRepository, Long> {

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
	
	@Autowired
	private IncomeRepository incomeRepository;

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
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.classifications(AbstractService.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.fields(AbstractService.findEntitiesByIds(fieldRepository, dto.getFieldIds(), Field.class))
				.type(AbstractService.findEntityById(typeRepository, dto.getTypeId(), Type.class))
				.careers(AbstractService.findEntitiesByIds(careerRepository, dto.getCareerIds(), Career.class))
				.address(dto.getAddress())
				.bankAccount(dto.getBankAccount())
				.bank(dto.getBank())
				.foundedDate(dto.getFoundedDate())
				.customerSince(dto.getCustomerSince())
				.income(findEntityById(incomeRepository, dto.getIncomeId(), Income.class))
				.website(dto.getWebsite())
				.build();
	}

	@Override
	protected Customer updateDtoToEntity(CustomerDto updateDto, Customer entity) throws ResourceNotFoundException {
		return createDtoToEntity(updateDto, entity);
	}

}
