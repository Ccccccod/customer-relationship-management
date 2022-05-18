/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;

/**
 * IDeleteService
 * @author Tuna
 *
 * @param <ID>
 */
public interface IDeleteService<ID extends Serializable> {
	
	void delete(Iterable<ID> ids);
	
	void delete(ID id);

}
