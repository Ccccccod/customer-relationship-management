/**
 * 
 */
package capstone.service.iservice;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.model.Named;
import capstone.repository.NamedJpaRepository;

/**
 * INamedService
 * @author Tuna
 */
public interface INamedService< //
		T extends BaseEntity<ID> & Named, //
		Repository extends JpaRepository<T, ID> & NamedJpaRepository<T, ID>, //
		ID extends Serializable //
> extends IReadNameService {
	
	Repository getRepository();
	
	@Override
	default List<?> getAllName() throws ResourceNotFoundException {
		List<IdAndName<ID>> idAndNames = this.getRepository().findIdNameAllBy();
		return idAndNames;
	}

}
