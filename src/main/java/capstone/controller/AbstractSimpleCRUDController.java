/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;

import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;

/**
 * Simple CRUD Controller. DTO, model, entity are the same object T
 * @author Tuna
 *
 * @param <T> Entity to CRUD
 * @param <ID> ID of &ltT&gt
 */
@RequestMapping("/default")
public abstract class AbstractSimpleCRUDController<T extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractCRUDController<T, T, T, T, ID> {
	
//	@Override
//	@GetMapping("/{page}/{size}")
//	public ResponseEntity<?> getAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
//		try {
//			Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
//			Page<T> pageTuts = this.repository.findAll(pageable);
//			List<T> ts = pageTuts.getContent();
//			
//			Map<String, Object> response = new HashMap<String, Object>();
//			response.put("list", ts);
//			response.put("currentPage", pageTuts.getNumber());
//			response.put("totalItems", pageTuts.getTotalElements());
//			response.put("totalPages", pageTuts.getTotalPages());
//			
//			return ResponseEntity.ok(response);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(null);
//		}
//	}
	
	@Override
	protected T entityToResponse(T entity) {
		return entity;
	}
	
	@Override
	protected T dtoToEntity(T createDto) throws ResourceNotFoundException {
		return createDto;
	}
	
	@Override
	protected void updateEntity(T updateDto, T entity) throws ResourceNotFoundException {
		entity = updateDto;
	}

}
