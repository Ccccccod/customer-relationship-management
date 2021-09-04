/**
 * 
 */
package capstone.service;

import capstone.entity.BusinessType;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.BusinessTypeRepository;

/**
 * BusinessTypeService
 * @author DELL
 */
public class BusinessTypeService extends AbstractService<BusinessType, BusinessType, BusinessType, BusinessType, BusinessTypeRepository, Long> {

	@Override
	protected Class<BusinessType> entityClass() {
		return BusinessType.class;
	}

	@Override
	protected BusinessType entityToResponse(BusinessType entity) {
		return entity;
	}

	@Override
	protected BusinessType createDtoToEntity(BusinessType d, BusinessType entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected BusinessType updateDtoToEntity(BusinessType updateDto, BusinessType entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
