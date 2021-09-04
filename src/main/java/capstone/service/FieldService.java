/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Field;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.FieldRepository;

/**
 * FieldService
 * @author DELL
 * @author tuna
 */
@Service
public class FieldService extends AbstractService<Field, Field, Field, Field, FieldRepository, Long> {

	@Override
	protected Class<Field> entityClass() {
		return Field.class;
	}

	@Override
	protected Field entityToResponse(Field entity) {
		return entity;
	}

	@Override
	protected Field createDtoToEntity(Field d, Field entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Field updateDtoToEntity(Field updateDto, Field entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
