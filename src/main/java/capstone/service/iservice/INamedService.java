/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.model.Identifiable;
import capstone.model.Named;
import capstone.repository.NamedJpaRepository;

/**
 * INamedService
 * @author Tuna
 */
public interface INamedService< //
		T extends Object & Identifiable<ID> & Named, //
		Repository extends JpaRepository<T, ID> & NamedJpaRepository<T, ID>, //
		ID extends Serializable //
> extends IReadNameService {
	
	Repository getRepository();
	
	@Override
	default List<IdAndName<ID>> getAllName() throws ResourceNotFoundException {
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.getRepository().findIdNameAllBy();
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	Session enableDeletedFilter(Boolean isDeleted);
	
	void disableDeletedFilter(Session session);

}
