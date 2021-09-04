/**
 * 
 */
package capstone.service;

import capstone.entity.Career;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;

/**
 * CareerService
 * @author DELL
 */
public class CareerService extends AbstractService<Career, Career, Career, Career, CareerRepository, Long> {

	@Override
	protected Class<Career> entityClass() {
		return Career.class;
	}

	@Override
	protected Career entityToResponse(Career entity) {
		return entity;
	}

	@Override
	protected Career createDtoToEntity(Career d, Career entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Career updateDtoToEntity(Career updateDto, Career entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
