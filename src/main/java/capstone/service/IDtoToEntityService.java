/**
 * 
 */
package capstone.service;

import java.io.Serializable;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;

/**
 * Dto to Entity mapper
 * @author Tuna
 *
 */
public interface IDtoToEntityService<Dto extends BaseDto<ID>, Entity extends BaseEntity<ID>, ID extends Serializable> {
	
	Entity dtoToEntity(Dto dto) throws ResourceNotFoundException;
	
}
