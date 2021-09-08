/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Unit;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.UnitRepository;
import capstone.service.iservice.INamedService;

/**
 * UnitService
 * @author Tuna
 */
@Service
public class UnitService extends AbstractService<Unit, Unit, Unit, Unit, UnitRepository, Long>
		implements INamedService<Unit, UnitRepository, Long> {

	@Override
	protected Class<Unit> entityClass() {
		return Unit.class;
	}

	@Override
	protected Unit entityToResponse(Unit entity) {
		return entity;
	}

	@Override
	protected Unit createDtoToEntity(Unit d, Unit entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Unit updateDtoToEntity(Unit updateDto, Unit entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
