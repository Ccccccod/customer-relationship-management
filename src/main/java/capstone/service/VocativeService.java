/**
 * 
 */
package capstone.service;

import capstone.entity.Vocative;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.VocativeRepository;

/**
 * VocativeService
 * @author Tuna
 */
public class VocativeService extends AbstractService<Vocative, Vocative, Vocative, Vocative, VocativeRepository, Long> {

	@Override
	protected Class<Vocative> entityClass() {
		return Vocative.class;
	}

	@Override
	protected Vocative entityToResponse(Vocative entity) {
		return entity;
	}

	@Override
	protected Vocative createDtoToEntity(Vocative d, Vocative entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Vocative updateDtoToEntity(Vocative updateDto, Vocative entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
