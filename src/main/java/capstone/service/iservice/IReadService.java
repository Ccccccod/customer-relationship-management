/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;
import java.util.List;

import capstone.exception.ResourceNotFoundException;

/**
 * IReadService
 * @author Tuna
 *
 * @param <T>
 */
public interface IReadService<T, ID extends Serializable> {
	
	List<T> getAll() throws ResourceNotFoundException;
	
	T getById(ID id) throws ResourceNotFoundException;

}
