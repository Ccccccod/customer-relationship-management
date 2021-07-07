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

import capstone.entity.Identifiable;
import capstone.exception.ErrorDetails;
import capstone.exception.ResourceNotFoundException;

/**
 * IDtoEntityController 
 * @author Tuna
 *
 * @param <Dto> the type of Dto
 * @param <Entity> the type of Entity
 * @param <ID> Id of Dto and Entity
 */
public interface IDtoEntityController<Dto extends Identifiable<ID>, Entity extends Identifiable<ID>, ID extends Serializable> {
	
	/**
	 * Return a {@link List} containing all of the Entity elements in database
	 * @return A {@link List} containing all of the Entity elements in database
	 */
	ResponseEntity<List<Entity>> getAll();

	/**
	 * Returns the element with the given id in database
	 * @param id the id to be used 
	 * @return An Entity in database with the given id
	 * @throws ResourceNotFoundException if no Entity is found
	 */
	ResponseEntity<Entity> getById(@PathVariable ID id) throws ResourceNotFoundException;
	
	/**
	 * Create the specified element and save it to database
	 * @param dto Element to be created and saved
	 * @return the Element that is added successfully. Or {@link ErrorDetails} if there's error
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 */
	ResponseEntity<?> create(@Valid @RequestBody Dto dto) throws ResourceNotFoundException;

	/**
	 * Create the specified element and save it to database
	 * @param id id of Element being created and saved. This will be used if the specified dto has id.
	 * @param dto Element to be created and saved
	 * @return the Element that is added successfully. Or {@link ErrorDetails} if there's error
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 */
	ResponseEntity<?> create(@PathVariable(value = "id") ID id, @Valid @RequestBody Dto dto) throws ResourceNotFoundException;
	
	/**
	 * Update element with given id
	 * @param id id of element being updated
	 * @param dto Element being updated
	 * @return the Element that is updated successfully. Or {@link ErrorDetails} if there's error
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 */
	ResponseEntity<Entity> update(@PathVariable(value = "id") ID id, @Valid @RequestBody Dto dto) throws ResourceNotFoundException;

	/**
	 * Delete elements with given ids
	 * @param ids ids of elements being deleted
	 * @return
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<?> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException;

}
