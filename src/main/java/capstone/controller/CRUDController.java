/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import capstone.dto.response.PageResponse;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;
import capstone.service.AbstractService;

/**
 * CRUDController
 * @author Tuna
 */
public abstract class CRUDController< //
		CreateDto extends Object & Identifiable<ID>, //
		UpdateDto extends Object & Identifiable<ID>, //
		Response extends Object & Identifiable<ID>, //
		Entity extends BaseEntity<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		Service extends AbstractService<CreateDto, UpdateDto, Response, Entity, Repository, ID>, //
		ID extends Serializable //
> //
{
	
	@Autowired
	private Service service;

	@GetMapping
	public ResponseEntity<PageResponse<Response>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) throws ResourceNotFoundException {
		PageResponse<Response> pageResponse = service.getAll(page, size);
		return ResponseEntity.ok(pageResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
		Response get = service.getById(id);
		return ResponseEntity.ok(get);
	}

	@PostMapping
	public ResponseEntity<Response> create(@Valid @RequestBody CreateDto dto) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException, ResourceNotFoundException, ResourceExistedException {
		Response create = service.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> update(@PathVariable(value = "id") ID id, @Valid @RequestBody UpdateDto dto)
			throws InstantiationException, IllegalAccessException, ResourceNotFoundException, ResourceExistedException {
		Response update = service.update(id, dto);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody List<ID> ids) throws ResourceNotFoundException {
		service.delete(ids);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("deleted", true);
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete1(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException {
		return delete(ids);
	}

}
