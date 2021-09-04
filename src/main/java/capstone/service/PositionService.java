/**
 * 
 */
package capstone.service;

import capstone.entity.Position;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PositionRepository;

/**
 * PositionService
 * @author DELL
 */
public class PositionService extends AbstractService<Position, Position, Position, Position, PositionRepository, Long>  {

	@Override
	protected Class<Position> entityClass() {
		return Position.class;
	}

	@Override
	protected Position entityToResponse(Position entity) {
		return entity ;
	}

	@Override
	protected Position createDtoToEntity(Position d, Position entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Position updateDtoToEntity(Position updateDto, Position entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
