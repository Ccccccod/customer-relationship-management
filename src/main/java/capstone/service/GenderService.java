/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Gender;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.GenderRepository;

/**
 * GenderService
 * @author DELL
 * @author tuna
 */
@Service
public class GenderService extends AbstractService<Gender, Gender, Gender, Gender, GenderRepository, Long>  {

	@Override
	protected Class<Gender> entityClass() {
		return Gender.class;
	}

	@Override
	protected Gender entityToResponse(Gender entity) {
		return entity;
	}

	@Override
	protected Gender createDtoToEntity(Gender d, Gender entity) throws ResourceNotFoundException {
		return  entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Gender updateDtoToEntity(Gender updateDto, Gender entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
