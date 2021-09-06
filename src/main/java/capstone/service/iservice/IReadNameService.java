/**
 * 
 */
package capstone.service.iservice;

import java.util.List;

import capstone.exception.ResourceNotFoundException;

/**
 * IReadNameService
 * @author Tuna
 *
 * @param <T>
 * @param <ID>
 */
public interface IReadNameService {
	
	List<?> getAllName() throws ResourceNotFoundException;

}
