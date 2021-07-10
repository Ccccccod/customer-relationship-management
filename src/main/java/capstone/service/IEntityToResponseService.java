/**
 * 
 */
package capstone.service;

import java.io.Serializable;

import capstone.dto.response.BaseResponse;
import capstone.entity.BaseEntity;

/**
 * Entity to Dto mapper
 * @author Tuna
 *
 */
@FunctionalInterface
public interface IEntityToResponseService<Response extends BaseResponse<ID>, Entity extends BaseEntity<ID>, ID extends Serializable> {
	
	Response entityToResponse(Entity entity);

}
