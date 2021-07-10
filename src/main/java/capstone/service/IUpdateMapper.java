/**
 * 
 */
package capstone.service;

import capstone.exception.ResourceNotFoundException;

/**
 * @author Tuna
 *
 */
@FunctionalInterface
public interface IUpdateMapper<Dto, Entity> {
	
	void updateEntity(Dto dto, Entity entity) throws ResourceNotFoundException;

}
