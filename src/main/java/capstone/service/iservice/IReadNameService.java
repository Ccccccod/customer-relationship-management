/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;
import java.util.List;

import capstone.model.IdAndName;

/**
 * IReadNameService
 * @author Tuna
 *
 * @param <T>
 * @param <ID>
 */
public interface IReadNameService<T, ID extends Serializable> {
	
	List<IdAndName<ID>> getAllName();

}
