/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.PhoneAreaCode;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PhoneAreaCodeRepository;

/**
 * PhoneAreaCodeService
 * @author Tuna
 */
@Service
public class PhoneAreaCodeService extends AbstractService<PhoneAreaCode, PhoneAreaCode, PhoneAreaCode, PhoneAreaCode, PhoneAreaCodeRepository, Long>{

	@Override
	protected Class<PhoneAreaCode> entityClass() {
		return PhoneAreaCode.class;
	}

	@Override
	protected PhoneAreaCode entityToResponse(PhoneAreaCode entity) {
		return entity;
	}

	@Override
	protected PhoneAreaCode createDtoToEntity(PhoneAreaCode d, PhoneAreaCode entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected PhoneAreaCode updateDtoToEntity(PhoneAreaCode updateDto, PhoneAreaCode entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
