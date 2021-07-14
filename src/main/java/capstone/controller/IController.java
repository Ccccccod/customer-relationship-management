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

import capstone.exception.ErrorDetails;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;

/**
 * IController
 * @author Tuna
 *
 * @param <CreateDto> the type of CreateDto
 * @param <UpdateDto> the type of UpdateDto
 * @param <Response> the type of Response
 * @param <Entity> the type of Entity
 * @param <ID> Id of CreateDto, UpdateDto, Response and Entity
 */
public interface IController<CreateDto extends Identifiable<ID>, UpdateDto extends Identifiable<ID>, Response extends Identifiable<ID>, Entity extends Identifiable<ID>, ID extends Serializable> {

	/**
	 * Return a {@link List} containing all of the Entity elements in database
	 * @return A {@link List} containing all of the Entity elements in database
	 */
	ResponseEntity<List<Response>> getAll();

	/**
	 * Returns the element with the given id in database
	 * @param id the id to be used 
	 * @return An Response in database with the given id
	 * @throws ResourceNotFoundException if no Entity is found
	 */
	ResponseEntity<Response> getById(@PathVariable ID id) throws ResourceNotFoundException;
	
	/**
	 * Create the specified element and save it to database
	 * @param dto Element to be created and saved
	 * @return the Element that is added successfully.
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 * @throws ResourceExistedException if there's already an entity with the given id
	 */
	ResponseEntity<Response> create(@Valid @RequestBody CreateDto createDto)
			throws ResourceNotFoundException, ResourceExistedException;

	/**
	 * Create the specified element and save it to database
	 * @param id id of Element being created and saved. This will be used if the specified dto has id.
	 * @param dto Element to be created and saved
	 * @return the Element that is added successfully.
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 * @throws ResourceExistedException if there's already an entity with the given id
	 */
	ResponseEntity<Response> create(@PathVariable(value = "id") ID id, @Valid @RequestBody CreateDto createDto)
			throws ResourceNotFoundException, ResourceExistedException;
	
	/**
	 * Update element with given id
	 * @param id id of element being updated
	 * @param dto Element being updated
	 * @return the Element that is updated successfully. Or {@link ErrorDetails} if there's error
	 * @throws ResourceNotFoundException if no Entity is found to create a field of the element
	 */
	ResponseEntity<Response> update(@PathVariable(value = "id") ID id, @Valid @RequestBody UpdateDto updateDto)
			throws ResourceNotFoundException;

	/**
	 * Delete elements with given ids
	 * @param ids ids of elements being deleted
	 * @return
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<?> delete(@PathVariable(value = "id") List<ID> ids) throws ResourceNotFoundException;

}
