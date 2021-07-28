/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;

/**
 * Dto Entity Controller. For Object with {@link Dto} and {@link Entity}
 * without <code>Model</code>
 * @author Tuna
 *
 * @param <Dto>
 * @param <Entity>
 * @param <ID> {@link Dto} and {@link Entity}'s ID
 */
@RequestMapping("/default")
public abstract class AbstractDtoEntityController<Dto extends BaseDto<ID>, //
		Entity extends BaseEntity<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		ID extends Serializable> //
		extends AbstractCRUDController<Dto, Dto, Entity, Entity, Repository, ID> {

	@Override
	protected Entity entityToResponse(Entity entity) {
		return entity;
	}
	
	@Override
	protected Entity updateEntity(Dto updateDto, Entity entity) throws ResourceNotFoundException {
		return this.dtoToEntity(updateDto);
	}

}
