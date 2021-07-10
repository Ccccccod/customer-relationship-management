/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import capstone.entity.BaseEntity;
import capstone.entity.Identifiable;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.service.UserService;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;

/**
 * Abstract CRUD Controller. 
 * @author Tuna
 *
 * @param <CreateDto> the type of CreateDto
 * @param <UpdateDto> the type of UpdateDto
 * @param <Response> the type of Response
 * @param <Entity> the type of Entity
 * @param <ID> Id of CreateDto, UpdateDto, Response and Entity
 */
public abstract class AbstractCRUDController<CreateDto extends Identifiable<ID>, UpdateDto extends Identifiable<ID>, //
		Response extends Identifiable<ID>, Entity extends BaseEntity<ID>, ID extends Serializable> {
	
	private Logger logger = LoggerFactory.getLogger(AbstractCRUDController.class);

	@Autowired
	protected JpaRepository<Entity, ID> repository;
	
	@Autowired
	protected UserService userService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Response>> getAll() {
		List<Response> response = this.repository.findAll().stream() //
				.map(e -> this.entityToResponse(e)) //
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
		Entity entity = this.repository.findById(id).orElse(null);
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@PostMapping({"", "/"})
	public ResponseEntity<Response> create(@Valid @RequestBody CreateDto dto) throws ResourceNotFoundException, ResourceExistedException {
        this.logger.debug("create() with body {} of type {}", dto, dto.getClass());
		if (! dto.isNew() && this.repository.existsById(dto.getId())) {
			throw new ResourceExistedException("An entity is already exist with id: " + dto.getId());
		}
		Entity entity = this.dtoToEntity(dto);
		entity.setCreatedBy(this.userService.getCurrentUser());
		entity = this.repository.save(entity);
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Response> create(@PathVariable(value = "id") ID id, @Valid @RequestBody CreateDto dto)
			throws ResourceNotFoundException, ResourceExistedException {
		dto.setId(id);
		return create(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> update(ID id, @Valid @RequestBody UpdateDto dto) throws ResourceNotFoundException {
		logger.debug("update() of id#{} with body {}", id, dto);
		
		Entity entity = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
		this.updateEntity(dto, entity);
		entity.setUpdatedBy(this.userService.getCurrentUser());
		
		entity = this.repository.save(entity);
		logger.debug("updated enitity: {}", entity);
		
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestBody List<ID> ids) throws ResourceNotFoundException {
		List<ID> deletedTS = this.repository.findAllById(ids).stream()
				.map(e -> e.getId())
				.collect(Collectors.toList());
		this.repository.deleteAllById(deletedTS);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedTS));
	}
	
	protected abstract Response entityToResponse(Entity entity);
	
	protected abstract Entity dtoToEntity(CreateDto createDto) throws ResourceNotFoundException;
	
	protected abstract void updateEntity(UpdateDto updateDto, Entity entity) throws ResourceNotFoundException;

}
