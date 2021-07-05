/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import capstone.entity.Identifiable;

/**
 * Simple CRUD Controller. DTO, model, entity are the same object T
 * @author Tuna
 *
 * @param <T> Entity to CRUD
 * @param <ID> ID of &ltT&gt
 */
public interface ISimpleCRUDController<T extends Identifiable<ID>, ID extends Serializable>
		extends IDtoEntityController<T, T, ID> {
	
	ResponseEntity<?> getAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size);

//	@DeleteMapping("/{id}")
//	default ResponseEntity<?> delete(@PathVariable(value = "id") ID id) throws ResourceNotFoundException {
//		return getRepository().findById(id).map(t -> {
//			getRepository().delete(t);
//			return ResponseEntity.ok(t);
//		}).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
//	}

}
