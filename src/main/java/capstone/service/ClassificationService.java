/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Classification;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ClassificationRepository;
import capstone.service.iservice.INamedService;

/**
 * ClassificationService
 * @author Tuna
 */
@Service
public class ClassificationService extends
		AbstractService<Classification, Classification, Classification, Classification, ClassificationRepository, Long>
		implements INamedService<Classification, ClassificationRepository, Long> {

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
