/**
 * 
 */
package capstone.service;

import capstone.entity.Classification;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ClassificationRepository;

/**
 * ClassificationService
 * @author Tuna
 */
public class ClassificationService extends AbstractService<Classification, Classification, Classification, Classification, ClassificationRepository, Long> {

	@Override
	protected Class<Classification> entityClass() {
		return Classification.class;
	}

	@Override
	protected Classification entityToResponse(Classification entity) {
		return entity;
	}

	@Override
	protected Classification createDtoToEntity(Classification d, Classification entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Classification updateDtoToEntity(Classification updateDto, Classification entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
