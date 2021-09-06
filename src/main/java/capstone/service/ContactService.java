/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.service.iservice.INamedService;

/**
 * ContactService
 * @author Tuna
 */
@Service
public class ContactService extends AbstractService<ContactDto, ContactDto, Contact, Contact, ContactRepository, Long>
		implements INamedService<Contact, ContactRepository, Long> {

//	@Autowired
//	private CustomerRepository customerRepository;
//	
//	@Autowired
//	private ClassificationRepository classificationRepository;
//	
//	@Autowired
//	private SourceRepository sourceRepository;
//
//	@Autowired
//	protected VocativeRepository vocativeRepository;
//
//	@Autowired
//	protected DepartmentRepository departmentRepository;
//
//	@Autowired
//	protected PositionRepository positionRepository;
//
//	@Autowired
//	protected GenderRepository genderRepository;
//
//	@Autowired
//	protected MaritalStatusRepository maritalStatusRepository;
//
//	@Autowired
//	protected BusinessTypeRepository businessTypeRepository;

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
				.vocative(vocativeService.getEntityById(d.getVocativeId()))
				.lastName(d.getLastName())
				.name(d.getName())
				.department(departmentService.getEntityById(d.getDepartmentId()))
				.position(positionService.getEntityById(d.getPositionId()))
				.customer(customerService.getEntityById(d.getCustomerId()))
				.classifications(classificationService.getEntitiesById(d.getClassificationIds()))
				.notCallPhone(d.getNotCallPhone())
				.notSendEmail(d.getNotSendEmail())
				.phone(d.getPhone())
				.officePhone(d.getOfficePhone())
				.otherPhone(d.getOtherPhone())
				.email(d.getEmail())
				.officeEmail(d.getOfficeEmail())
				.source(sourceService.getEntityById(d.getSourceId()))
				.address(d.getAddress())
				.dateOfBirth(d.getDateOfBirth())
				.gender(genderService.getEntityById(d.getGenderId()))
				.maritalStatus(maritalStatusService.getEntityById(d.getMaritalStatusId()))
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
