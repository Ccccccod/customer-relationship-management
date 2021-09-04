/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.PotentialDto;
import capstone.entity.BusinessType;
import capstone.entity.Classification;
import capstone.entity.Department;
import capstone.entity.Gender;
import capstone.entity.Position;
import capstone.entity.Potential;
import capstone.entity.Source;
import capstone.entity.Vocative;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.BusinessTypeRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.DepartmentRepository;
import capstone.repository.GenderRepository;
import capstone.repository.PositionRepository;
import capstone.repository.PotentialRepository;
import capstone.repository.SourceRepository;
import capstone.repository.VocativeRepository;

/**
 * PotentialService
 * @author Tuna
 * 
 */
@Service
public class PotentialService
		extends AbstractService<PotentialDto, PotentialDto, Potential, Potential, PotentialRepository, Long> {

	@Override
	protected Class<Potential> entityClass() {
		return Potential.class;
	}

	@Autowired
	protected VocativeRepository vocativeRepository;

	@Autowired
	protected DepartmentRepository departmentRepository;

	@Autowired
	protected PositionRepository positionRepository;

	@Autowired
	protected ClassificationRepository classificationRepository;

	@Autowired
	protected SourceRepository sourceRepository;

	@Autowired
	protected GenderRepository genderRepository;

	@Autowired
	protected BusinessTypeRepository businessTypeRepository;
	
	@Override
 	protected Potential entityToResponse(Potential entity) {
		return entity;
	}

	@Override
	protected Potential createDtoToEntity(PotentialDto d, Potential entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.vocative(findEntityById(vocativeRepository, d.getVocativeId(), Vocative.class))
				.lastName(d.getLastName())
				.name(d.getName())
				.department(findEntityById(departmentRepository, d.getDepartmentId(), Department.class))
				.position(findEntityById(positionRepository, d.getPositionId(), Position.class))
				.phone(d.getPhone())
				.officePhone(d.getOfficePhone())
				.otherPhone(d.getOtherPhone())
				.classifications(findEntitiesByIds(classificationRepository, d.getClassificationIds(), Classification.class))
				.source(findEntityById(sourceRepository, d.getSourceId(), Source.class))
				.notCallPhone(d.getNotCallPhone())
				.notSendEmail(d.getNotSendEmail())
				.email(d.getEmail())
				.officeEmail(d.getOfficeEmail())
				.customer(d.getCustomer())
				.taxCode(d.getTaxCode())
				.customerTaxCode(d.getCustomerTaxCode())
				.address(d.getAddress())
				.gender(findEntityById(genderRepository, d.getGenderId(), Gender.class))
				.dateOfBirth(d.getDateOfBirth())
				.facebook(d.getFacebook())
				.bankAccount(d.getBankAccount())
				.bank(d.getBank())
				.foundedDate(d.getFoundedDate())
				.businessType(findEntityById(businessTypeRepository, d.getBusinessTypeId(), BusinessType.class))
				.build();
	}

	@Override
	protected Potential updateDtoToEntity(PotentialDto updateDto, Potential entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
