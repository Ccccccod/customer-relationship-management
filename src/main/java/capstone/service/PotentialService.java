/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.dto.request.PotentialDto;
import capstone.entity.Potential;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PotentialRepository;
import capstone.service.iservice.INamedService;

/**
 * PotentialService
 * @author Tuna
 */
@Service
public class PotentialService
		extends AbstractService<PotentialDto, PotentialDto, Potential, Potential, PotentialRepository, Long>
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
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
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

}
