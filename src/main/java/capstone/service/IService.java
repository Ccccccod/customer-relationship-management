/**
 * 
 */
package capstone.service;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.exception.ResourceNotFoundException;

/**
 * @author Tuna
 *
 */
public interface IService<Dto, Entity, ID> {
	
	Entity dtoToEntity(Dto dto) throws ResourceNotFoundException;
	
}
