/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;

import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;

/**
 * IUpdateService
 * @author Tuna
 *
 * @param <T>
 * @param <R>
 */
public interface IUpdateService<T, R, ID extends Serializable> {

	R update(ID id, T t)
			throws ResourceNotFoundException, InstantiationException, IllegalAccessException, ResourceExistedException;

}
