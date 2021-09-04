/**
 * 
 */
package capstone.service.iservice;

import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;

/**
 * ICreateService
 * @author Tuna
 *
 * @param <T>
 * @param <R>
 */
public interface ICreateService<T, R> {

	R create(T t) throws ResourceNotFoundException, ResourceExistedException, IllegalArgumentException,
			IllegalAccessException, InstantiationException;

}
