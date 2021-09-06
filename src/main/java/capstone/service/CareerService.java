/**
 * 
 */
package capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import capstone.entity.Career;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;
import capstone.repository.CareerRepository.IdAndNameAndField;
import capstone.service.iservice.INamedService;

/**
 * CareerService
 * @author DELL
 * @author tuna
 */
@Service
public class CareerService extends AbstractService<Career, Career, Career, Career, CareerRepository, Long>
		implements INamedService<Career, CareerRepository, Long> {

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
				.field(fieldService.getById(d.getFieldId()))
				.build();
	}

	@Override
	protected Career updateDtoToEntity(Career updateDto, Career entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}
	
	@Override
	public List<?> getAllName() throws ResourceNotFoundException {
		List<IdAndNameAndField> list = deletedFilter(() -> this.repository.FindIdNameFieldAllBy(), false);
		return list;
	}

}
