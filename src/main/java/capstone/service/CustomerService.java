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
		Customer customer = new Customer();
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setShortName(dto.getShortName());
		customer.setTaxCode(dto.getTaxCode());
		customer.setPhone(dto.getPhone());
		customer.setEmail(dto.getEmail());
		customer.setAddress(dto.getAddress());
		// Source
		customer.setSource(this.idToObj(sourceRepository, dto.getSourceId(), Source.class));
		// Classification
		if (!Objects.isNull(dto.getClassificationIds())) {
			Set<Classification> classifications = new HashSet<>();
			for (Long classifionId : dto.getClassificationIds()) {
				classifications.add(classificationRepository.findById(classifionId)
						.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(Classification.class, classifionId)));
			}
			customer.setClassifications(classifications);
		}
		// Field
		if (!Objects.isNull(dto.getFieldIds())) {
			Set<Field> fields = new HashSet<>();
			for (Long fieldIds : dto.getFieldIds()) {
				fields.add(fieldRepository.findById(fieldIds)
						.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(Field.class, fieldIds)));
			}
			customer.setFields(fields);
		}
		// Type
		if (!Objects.isNull(dto.getTypeId())) {
			customer.setType(typeRepository.findById(dto.getTypeId())
					.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(Type.class, dto.getTypeId())));
		}
		// Career
		if (!Objects.isNull(dto.getCareerIds())) {
			Set<Career> careers = new HashSet<>();
			for (Long careerIds : dto.getCareerIds()) {
				careers.add(careerRepository.findById(careerIds)
						.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(Career.class, careerIds)));
			}
			customer.setCareers(careers);
		}
		
		return customer;
	}

}
