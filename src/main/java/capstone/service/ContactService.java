/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.ContactDto;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Department;
import capstone.entity.Gender;
import capstone.entity.MaritalStatus;
import capstone.entity.Position;
import capstone.entity.Source;
import capstone.entity.Vocative;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.BusinessTypeRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.DepartmentRepository;
import capstone.repository.GenderRepository;
import capstone.repository.MaritalStatusRepository;
import capstone.repository.PositionRepository;
import capstone.repository.SourceRepository;
import capstone.repository.VocativeRepository;

/**
 * ContactService
 * @author Tuna
 */
@Service
public class ContactService extends AbstractService<ContactDto, ContactDto, Contact, Contact, ContactRepository, Long> {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Autowired
	private SourceRepository sourceRepository;

	@Autowired
	protected VocativeRepository vocativeRepository;

	@Autowired
	protected DepartmentRepository departmentRepository;

	@Autowired
	protected PositionRepository positionRepository;

	@Autowired
	protected GenderRepository genderRepository;

	@Autowired
	protected MaritalStatusRepository maritalStatusRepository;

	@Autowired
	protected BusinessTypeRepository businessTypeRepository;

	@Override
	protected Class<Contact> entityClass() {
		return Contact.class;
	}

	@Override
	protected Contact entityToResponse(Contact entity) {
		return entity;
	}

	@Override
	protected Contact createDtoToEntity(ContactDto d, Contact entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.vocative(findEntityById(vocativeRepository, d.getVocativeId(), Vocative.class))
				.lastName(d.getLastName())
				.name(d.getName())
				.department(findEntityById(departmentRepository, d.getDepartmentId(), Department.class))
				.position(findEntityById(positionRepository, d.getPositionId(), Position.class))
				.customer(findEntityById(customerRepository, d.getCustomerId(), Customer.class))
				.classifications(findEntitiesByIds(classificationRepository, d.getClassificationIds(), Classification.class))
				.notCallPhone(d.getNotCallPhone())
				.notSendEmail(d.getNotSendEmail())
				.phone(d.getPhone())
				.officePhone(d.getOfficePhone())
				.otherPhone(d.getOtherPhone())
				.email(d.getEmail())
				.officeEmail(d.getOfficeEmail())
				.source(findEntityById(sourceRepository, d.getSourceId(), Source.class))
				.address(d.getAddress())
				.dateOfBirth(d.getDateOfBirth())
				.gender(findEntityById(genderRepository, d.getGenderId(), Gender.class))
				.maritalStatus(findEntityById(maritalStatusRepository, d.getMaritalStatusId(), MaritalStatus.class))
				.facebook(d.getFacebook())
				.bankAccount(d.getBankAccount())
				.bank(d.getBank())
				.build();
	}

	@Override
	protected Contact updateDtoToEntity(ContactDto updateDto, Contact entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
