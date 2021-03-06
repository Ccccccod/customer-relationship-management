/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;
import capstone.model.Repositoried;
import capstone.service.UserService;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;
import lombok.Getter;

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
public abstract class AbstractCRUDController< //
			CreateDto extends Object & Identifiable<ID>, //
			UpdateDto extends Object & Identifiable<ID>, //
			Response extends Object & Identifiable<ID>, //
			Entity extends BaseEntity<ID>, //
			Repository extends JpaRepository<Entity, ID>, //
			ID extends Serializable //
		> //
		implements Repositoried<Repository> {
	
	protected abstract Class<Entity> entityClass();
	
	@Getter
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Getter
	protected Repository repository;

	@Autowired
	@Getter
	protected UserService userService;
	
	protected ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Response>> getAll() {
		List<Response> response = this.repository.findAll().stream() //
				.map(e -> this.entityToResponse(e)) //
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
		Entity entity = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id, entityClass()));
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Response> create(@Valid @RequestBody CreateDto dto) throws ResourceNotFoundException,
			ResourceExistedException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		this.logger.debug("create() with body {} of type {}", dto, dto.getClass());
        
		Entity entity = this.dtoToEntity(dto, entityClass().newInstance());
		entity.setCreatedBy(this.userService.getCurrentUser());
		entity.setId(null);
		
		capstone.utils.RepositoryUtils.checkExistedFields(entity, this.repository, entityClass());
		entity = this.repository.save(entity);
		
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
//		return ResponseEntity.ok(null);
	}

//	@PostMapping("/{id}")
//	public ResponseEntity<Response> create(@PathVariable(value = "id") ID id, @Valid @RequestBody CreateDto dto)
//			throws ResourceNotFoundException, ResourceExistedException {
//		dto.setId(id);
//		return create(dto);
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> update(@PathVariable(value = "id") ID id, @Valid @RequestBody UpdateDto dto)
			throws ResourceNotFoundException, InstantiationException, IllegalAccessException, ResourceExistedException {
		logger.debug("update() of id#{} with body {}", id, dto);
		
		Entity entity = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id, entityClass()));
		entity = this.updateEntity(dto, entity);
		entity.setUpdatedBy(this.userService.getCurrentUser());
		entity.setId(id);
		
		capstone.utils.RepositoryUtils.checkExistedFields(entity, id, repository, entityClass());
		entity = this.repository.saveAndFlush(entity);
		logger.debug("updated enitity: {}", entity);
		
		Response response = this.entityToResponse(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> delete(@RequestBody List<ID> ids) throws ResourceNotFoundException {
		List<Entity> entities = this.repository.findAllById(ids).stream()
				.collect(Collectors.toList());
		this.preDelete(entities);
		this.repository.deleteAll(entities);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", true));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete1(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException {
		return delete(ids);
	}
	
	/**
	 * Entity to Response mapper
	 * @param entity
	 * @return
	 */
	protected abstract Response entityToResponse(Entity entity);
	
	/**
	 * Dto to Entity mapper
	 * @param createDto dto to map to entity
	 * @param entity
	 * @return
	 * @throws ResourceNotFoundException if Dto's fields contain id of no resources
	 */
	protected abstract Entity dtoToEntity(CreateDto createDto, Entity entity) throws ResourceNotFoundException;
	
	/**
	 * Update Entity's fields from UpdateDto's fields
	 * @param updateDto
	 * @param entity
	 * @return
	 * @throws ResourceNotFoundException if Dto's fields contain id of no resources
	 */
	protected abstract Entity updateEntity(UpdateDto updateDto, Entity entity) throws ResourceNotFoundException;
	
	protected void preDelete(List<Entity> entities) {
		entities.forEach(e -> {
			e.setCreatedBy(null);
			e.setUpdatedBy(null);
		});
		this.getRepository().saveAll(entities);
	}
	
//	@SuppressWarnings("unchecked")
//	protected void checkExistedFields(Entity entity)
//			throws ResourceExistedException, IllegalArgumentException, IllegalAccessException, InstantiationException {
//		// Get all unique fields
//		List<Field> fields = new LinkedList<>();
//		for (Class<?> class1 = entity.getClass(); !Objects.isNull(class1); class1 = class1.getSuperclass()) {
//			fields.addAll(Arrays.asList(class1.getDeclaredFields()));
//		}
//		// Remove fields that don't need to be unique
//		fields = fields.stream()
//				.filter(Objects::nonNull)
//				// Ignore id
//				.filter(field -> !field.getName().toLowerCase().equals("id"))
//				.filter(f -> Arrays.stream(f.getDeclaredAnnotationsByType(Column.class)).anyMatch(Column::unique))
//				.collect(Collectors.toList());
//		
//		// Check if an entity already exist
//		for (Field field : fields) {
//			// Create new instance to make an Example to check
//			Entity instance = ((Class<Entity>) entity.getClass()).newInstance();
//			field.setAccessible(true);
//			Object value = field.get(entity);
//			field.set(instance, value);
//			Example<Entity> example = Example.of(instance);
//			// Query
//			if (this.repository.exists(example)) {
//				throw new ResourceExistedException("An entity already exist", field.getName(), value);
//			}
//		}
//	}

}
