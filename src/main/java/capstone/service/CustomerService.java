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
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.FieldRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;

/**
 * Customer Service
 * @author Tuna
 *
 */
@Service
public class CustomerService extends AbstractService implements IDtoToEntityService<CustomerDto, Customer, Long> {

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
	public Customer dtoToEntity(CustomerDto dto) throws ResourceNotFoundException {
		Customer customer = Customer.builder()
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
				.fields(AbstractService.findEntitiesByIds(fieldRepository, dto.getFieldIds(), Field.class))
				.type(AbstractService.findEntityById(typeRepository, dto.getTypeId(), Type.class))
				.careers(AbstractService.findEntitiesByIds(careerRepository, dto.getCareerIds(), Career.class))
				.build();
		return customer;
	}

}
