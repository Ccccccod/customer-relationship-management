/**
 * 
 */
package capstone.service;

import java.util.LinkedHashSet;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import capstone.dto.request.PotentialDto;
import capstone.entity.Contact;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Potential;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PotentialRepository;
import capstone.service.iservice.INamedService;

/**
 * PotentialService
 * @author Tuna
 */
@Service
public class PotentialService
		extends CodedService<PotentialDto, PotentialDto, Potential, Potential, PotentialRepository, Long>
		implements INamedService<Potential, PotentialRepository, Long> {

	@Override
	protected Class<Potential> entityClass() {
		return Potential.class;
	}
	
	@Override
 	protected Potential entityToResponse(Potential entity) {
		return entity;
	}

	@Override
	protected Potential createDtoToEntity(PotentialDto d, Potential entity) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		return entity.toBuilder()
				.id(d.getId())
//				.code(d.getCode())
				.vocative(vocativeService.getEntityById(d.getVocativeId()))
				.lastName(d.getLastName())
				.name(d.getName())
				.department(departmentService.getEntityById(d.getDepartmentId()))
				.position(positionService.getEntityById(d.getPositionId()))
				.phone(d.getPhone())
				.phoneAreaCode(phoneAreaCodeService.getEntityById(d.getPhoneAreaCodeId()))
				.officePhone(d.getOfficePhone())
				.otherPhone(d.getOtherPhone())
				.classifications(classificationService.getEntitiesById(d.getClassificationIds()))
				.source(sourceService.getEntityById(d.getSourceId()))
				.notCallPhone(d.getNotCallPhone())
				.notSendEmail(d.getNotSendEmail())
				.email(d.getEmail())
				.officeEmail(d.getOfficeEmail())
				.customer(d.getCustomer())
				.taxCode(d.getTaxCode())
				.customerTaxCode(d.getCustomerTaxCode())
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.gender(genderService.getEntityById(d.getGenderId()))
				.dateOfBirth(d.getDateOfBirth())
				.facebook(d.getFacebook())
				.bankAccount(d.getBankAccount())
				.bank(d.getBank())
				.foundedDate(d.getFoundedDate())
				.businessType(businessTypeService.getEntityById(d.getBusinessTypeId()))
				.build();
	}

	@Override
	protected Potential updateDtoToEntity(PotentialDto updateDto, Potential entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

	public Contact singleConvert(Long id, Long customerId)
			throws ResourceNotFoundException, InstantiationException, IllegalAccessException, ResourceExistedException {
		Potential p = getById(id);
		Customer customer = null;
		Contact contact;
		boolean createNewCustomer = false;
		if (customerId != null) {
			customer = customerService.getById(customerId);
		}
		if (customer == null && p.getCustomer() != null && !p.getCustomer().isEmpty()) {
			customer = Customer.builder()
					.code(randomStringGenerator.generate(10))
					.name(p.getCustomer())
					.phone(p.getOfficePhone())
					.bankAccount(p.getBankAccount())
					.bank(p.getBank())
					.foundedDate(p.getFoundedDate())
					.fields(p.getFields())
					.careers(p.getCareers())
					.income(p.getIncome())
					.website(p.getWebsite())
					.contacts(new LinkedHashSet<>())
					.build();
			createNewCustomer = true;
		}
		contact = Contact.builder()
				.code(randomStringGenerator.generate(10))
				.vocative(p.getVocative())
				.lastName(p.getLastName())
				.name(p.getName())
				.department(p.getDepartment())
				.position(p.getPosition())
				.classifications(p.getClassifications())
				.notCallPhone(p.getNotCallPhone())
				.notSendEmail(p.getNotSendEmail())
				// TODO: phoneAreaCode
				.phone(p.getPhone())
				.officePhone(p.getOfficePhone())
				.otherPhone(p.getOtherPhone())
				.email(p.getEmail())
				.officeEmail(p.getOfficeEmail())
				.source(p.getSource())
				.country(p.getCountry())
				.province(p.getProvince())
				.district(p.getDistrict())
				.ward(p.getWard())
				.address(p.getAddress())
				.dateOfBirth(p.getDateOfBirth())
				.gender(p.getGender())
//				.maritalStatus(p.getMaritalStatus())
				.facebook(p.getFacebook())
				.bankAccount(p.getBankAccount())
				.bank(p.getBank())
				.customer(customer)
				.build();
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			synchronized (this) {
				synchronized (contactService) {
					contactService.checkExist(contact);
					synchronized (customerService) {
						if (customer != null) {
							if (customer.getContacts() == null)
								customer.setContacts(new LinkedHashSet<>());
							customer.getContacts().add(contact);
							customerService.checkExist(customer);
						}
						if (customer != null && createNewCustomer) {
							customerService.saveEntity(customer);
						}
						contact = contactService.saveEntity(contact);
					}
				}
				this.delete(id);
			}
			return contact;
		} finally {
			disableDeletedFilter(session);
		}
	}

}
