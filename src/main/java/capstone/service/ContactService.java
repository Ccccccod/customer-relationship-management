/**
 * 
 */
package capstone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;
import capstone.entity.Country;
import capstone.entity.District;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.service.iservice.INamedService;

/**
 * ContactService
 * @author Tuna
 */
@Service
public class ContactService extends CodedService<ContactDto, ContactDto, Contact, Contact, ContactRepository, Long>
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
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
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
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
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
