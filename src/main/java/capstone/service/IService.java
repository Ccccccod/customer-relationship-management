/**
 * 
 */
package capstone.service;

import capstone.exception.ResourceNotFoundException;

/**
 * @author Tuna
 *
 */
public interface IService<Dto, Entity, ID> {
	
	Entity dtoToEntity(Dto dto) throws ResourceNotFoundException;
	
}
