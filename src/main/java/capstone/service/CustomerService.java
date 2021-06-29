/**
 * 
 */
package capstone.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import capstone.utils.DtoUtils;

/**
 * Customer Service
 * @author Tuna
 *
 */
@Service
public class CustomerService extends AbstractService<CustomerDto, Customer, Long> {
	
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
				.name(dto.getName())
				.shortName(dto.getShortName())
				.taxCode(dto.getTaxCode())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.address(dto.getAddress())
				.source(this.idToObj(sourceRepository, dto.getSourceId(), Source.class))
				.classifications(this.idToObj(classificationRepository, dto.getClassificationIds(), Classification.class))
				.fields(this.idToObj(fieldRepository, dto.getFieldIds(), Field.class))
				.type(this.idToObj(typeRepository, dto.getTypeId(), Type.class))
				.careers(this.idToObj(careerRepository, dto.getCareerIds(), Career.class))
				.build();
		return customer;
	}

}
