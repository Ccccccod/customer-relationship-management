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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.dto.request.BaseDto;
import capstone.dto.response.BaseResponse;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.service.IDtoToEntityService;
import capstone.service.IEntityToResponseService;
import capstone.service.UserService;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;

/**
 * Abstract CRUD Controller. 
 * @author Tuna
 *
 * @param <Dto>
 * @param <Response>
 * @param <Entity>
 * @param <ID>
 */
@RequestMapping("/default")
public abstract class AbstractCRUDController<Dto extends BaseDto<ID>, Response extends BaseResponse<ID>, //
		Entity extends BaseEntity<ID>, ID extends Serializable> implements IController<Dto, Response, Entity, ID> {

	@Autowired
	protected JpaRepository<Entity, ID> repository;
	
	@Autowired
	protected IDtoToEntityService<Dto, Entity, ID> dtoToEntityService;
	
	@Autowired
	protected IEntityToResponseService<Response, Entity, ID> entityToResponseService;
	
	@Autowired
	protected UserService userService;
	
	@Override
	@GetMapping({"", "/"})
	public ResponseEntity<List<Response>> getAll() {
		List<Response> response = this.repository.findAll().stream() //
				.map(e -> this.entityToResponseService.entityToResponse(e)) //
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
		Entity entity = this.repository.findById(id).orElse(null);
		Response response = this.entityToResponseService.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping({"", "/"})
	public ResponseEntity<Response> create(@Valid @RequestBody Dto dto) throws ResourceNotFoundException, ResourceExistedException {
		if (! dto.isNew() && this.repository.existsById(dto.getId())) {
			throw new ResourceExistedException("An entity is already exist with id: " + dto.getId());
		}
		Entity entity = this.dtoToEntityService.dtoToEntity(dto);
		entity.setCreatedBy(this.userService.getCurrentUser());
		entity = this.repository.save(entity);
		Response response = this.entityToResponseService.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/{id}")
	public ResponseEntity<Response> create(@PathVariable(value = "id") ID id, @Valid @RequestBody Dto dto)
			throws ResourceNotFoundException, ResourceExistedException {
		dto.setId(id);
		return create(dto);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Response> update(ID id, @Valid @RequestBody Dto dto) throws ResourceNotFoundException {
		if (!this.repository.existsById(id)) {
			DtoUtils.throwResourceNotFoundException(id);
		}
		dto.setId(id);
		Entity entity = this.dtoToEntityService.dtoToEntity(dto);
		entity.setUpdatedBy(this.userService.getCurrentUser());
		entity = this.repository.save(entity);
		Response response = this.entityToResponseService.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(List<ID> ids) throws ResourceNotFoundException {
		List<ID> deletedTS = this.repository.findAllById(ids).stream()
				.map(e -> e.getId())
				.collect(Collectors.toList());
		this.repository.deleteAllById(deletedTS);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedTS));
	}

}
