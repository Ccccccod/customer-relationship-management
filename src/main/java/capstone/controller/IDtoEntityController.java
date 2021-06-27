/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;

/**
 * @author Tuna
 *
 */
public interface IDtoEntityController<Dto extends BaseDto<ID>, Entity extends BaseEntity<ID>, ID extends Serializable> {
	
	ResponseEntity<List<Entity>> getAll();

	ResponseEntity<Entity> getById(@PathVariable ID id) throws ResourceNotFoundException;
	
	ResponseEntity<?> create(@Valid @RequestBody Dto dto) throws ResourceNotFoundException;
	
	ResponseEntity<?> create(@PathVariable(value = "id") ID id, @Valid @RequestBody Dto dto) throws ResourceNotFoundException;
	
	ResponseEntity<Entity> update(@PathVariable(value = "id") ID id, @Valid @RequestBody Dto dto) throws ResourceNotFoundException;

	ResponseEntity<?> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException;

}
