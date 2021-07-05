/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.entity.BaseEntity;
import capstone.exception.ErrorDetails;
import capstone.exception.ResourceNotFoundException;
import capstone.utils.DtoUtils;
import capstone.utils.MapBuilder;

/**
 * Simple CRUD Controller. DTO, model, entity are the same object T
 * @author Tuna
 *
 * @param <T> Entity to CRUD
 * @param <ID> ID of &ltT&gt
 */
@RequestMapping("/default")
public abstract class AbstractSimpleCRUDController<T extends BaseEntity<ID>, ID extends Serializable>
		implements ISimpleCRUDController<T, ID> {
	
	@Autowired
	private JpaRepository<T, ID> repository;

	@Override
	@GetMapping({"", "/"})
	public ResponseEntity<List<T>> getAll() {
		return ResponseEntity.ok(this.repository.findAll());	
	}
	
	@Override
	@GetMapping("/{page}/{size}")
	public ResponseEntity<?> getAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
			Page<T> pageTuts = this.repository.findAll(pageable);
			List<T> ts = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("list", ts);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(ID id) throws ResourceNotFoundException {
		return ResponseEntity.ok(this.repository.findById(id) //
				.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id)));
	}
	
	@Override
	@PostMapping({"", "/"})
	public ResponseEntity<?> create(@Valid @RequestBody T t) throws ResourceNotFoundException {
		if (! t.isNew() && this.repository.existsById(t.getId())) {
			return ResponseEntity.badRequest().body(new ErrorDetails("An entity is already exist with id: " + t.getId()));
		}
		return ResponseEntity.ok(this.repository.save(t)); 
	}
	
	@Override
	@PostMapping("/{id}")
	public ResponseEntity<?> create(@PathVariable(value = "id") ID id, @Valid @RequestBody T t) throws ResourceNotFoundException {
		t.setId(id);
		return create(t);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable(value = "id") ID id, @Valid @RequestBody T t) throws ResourceNotFoundException {
		if (!this.repository.existsById(id)) {
			DtoUtils.throwResourceNotFoundException(id);
		}
		t.setId(id);
		return ResponseEntity.ok(this.repository.save(t));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException {
		List<ID> deletedIds = this.repository.findAllById(ids).stream()
				.map(t -> t.getId())
				.collect(Collectors.toList());
		this.repository.deleteAllById(deletedIds);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedIds));
	}

}
