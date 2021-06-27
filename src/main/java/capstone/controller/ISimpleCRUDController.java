/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import capstone.entity.Identifiable;
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
public interface ISimpleCRUDController<T extends BaseEntity<ID> & Identifiable<ID>, ID extends Serializable> {
	
	/**
	 * @return Repository for T
	 */
	JpaRepository<T, ID> getRepository();
	
	/**
	 * List
	 * @return
	 */
	@GetMapping({"", "/"})
	default ResponseEntity<List<T>> getAll(){
		return ResponseEntity.ok(getRepository().findAll());
	}
	
	@GetMapping("/{page}/{size}")
	default ResponseEntity<?> getAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
			Page<T> pageTuts = getRepository().findAll(pageable);
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

	@GetMapping("/{id}")
	default ResponseEntity<T> getById(@PathVariable ID id) throws ResourceNotFoundException {
		return ResponseEntity.ok(getRepository().findById(id) //
				.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id)));
	}
	
	@PostMapping({"", "/"})
	default ResponseEntity<?> create(@Valid @RequestBody T t) {
		if (! t.isNew() && getRepository().existsById(t.getId())) {
			return ResponseEntity.badRequest().body(new ErrorDetails("An entity is already exist with id: " + t.getId()));
		}
		return ResponseEntity.ok(getRepository().save(t)); 
	}
	
	@PostMapping("/{id}")
	default ResponseEntity<?> create(@PathVariable(value = "id") ID id, @Valid @RequestBody T t) {
		t.setId(id);
		return create(t);
	}
	
	@PutMapping("/{id}")
	default ResponseEntity<T> update(@PathVariable(value = "id") ID id, @Valid @RequestBody T t) throws ResourceNotFoundException {
		if (!getRepository().existsById(id)) {
			DtoUtils.throwResourceNotFoundException(id);
		}
		t.setId(id);
		return ResponseEntity.ok(getRepository().save(t));
	}

//	@DeleteMapping("/{id}")
//	default ResponseEntity<?> delete(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
//		return getRepository().findById(id).map(t -> {
//			getRepository().delete(t);
//			return ResponseEntity.ok(t);
//		}).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
//	}

	@DeleteMapping("/{id}")
	default ResponseEntity<Map<String, List<T>>> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException {
		List<T> deletedTS = getRepository().findAllById(ids);
		getRepository().deleteAll(deletedTS);
		return ResponseEntity.ok(MapBuilder.hashMap("deleted", deletedTS));
	}

}
