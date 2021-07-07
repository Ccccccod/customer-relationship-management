/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;
import capstone.exception.ErrorDetails;
import capstone.exception.ResourceNotFoundException;
import capstone.service.IDtoToEntityService;
import capstone.service.UserService;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;

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
public abstract class AbstractDtoEntityController<Dto extends BaseDto<ID>, Entity extends BaseEntity<ID>, ID extends Serializable>
		implements IDtoEntityController<Dto, Entity, ID> {

	@Autowired
	protected JpaRepository<Entity, ID> repository;
	
	@Autowired
	protected IDtoToEntityService<Dto, Entity, ID> service;
	
	@Autowired
	protected UserService userService;
	
	@Override
	@GetMapping({"", "/"})
	public ResponseEntity<List<Entity>> getAll() {
		return ResponseEntity.ok(this.repository.findAll());
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Entity> getById(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
		return ResponseEntity.ok(this.repository.findById(id) //
				.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id)));
	}	
	
	@Override
	@PostMapping({"", "/"})
	public ResponseEntity<?> create(@Valid Dto dto) throws ResourceNotFoundException {
		if (! dto.isNew() && this.repository.existsById(dto.getId())) {
			return ResponseEntity.badRequest().body(new ErrorDetails("An entity is already exist with id: " + dto.getId()));
		}
		Entity entity = this.service.dtoToEntity(dto);
		entity.setCreatedBy(this.userService.getCurrentUser());
		return ResponseEntity.ok(this.repository.save(entity));
	}
	
	@Override
	@PostMapping("/{id}")
	public ResponseEntity<?> create(@PathVariable(value = "id") ID id, @Valid Dto dto) throws ResourceNotFoundException {
		dto.setId(id);
		return create(dto);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Entity> update(@PathVariable(value = "id") ID id, @Valid Dto dto) throws ResourceNotFoundException {
		if (!this.repository.existsById(id)) {
			DtoUtils.throwResourceNotFoundException(id);
		}
		dto.setId(id);
		Entity entity = this.service.dtoToEntity(dto);
		entity.setUpdatedBy(this.userService.getCurrentUser());
		return ResponseEntity.ok(this.repository.save(entity));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException {
		List<ID> deletedTS = this.repository.findAllById(ids).stream()
				.map(e -> e.getId())
				.collect(Collectors.toList());
		this.repository.deleteAllById(deletedTS);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedTS));
	}

}
