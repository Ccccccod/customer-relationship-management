/**
 * 
 */
package capstone.service.iservice;

import java.util.List;

import capstone.exception.ResourceNotFoundException;

/**
 * IReadNameService
 * @author Tuna
 */
public interface IReadNameService {
	
	List<?> getAllName() throws ResourceNotFoundException;

}
