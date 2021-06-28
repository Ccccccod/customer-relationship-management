/**
 * 
 */
package capstone.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;
import capstone.utils.DtoUtils;

/**
 * Abstract Service
 * @author Tuna
 */
public abstract class AbstractService<Dto extends BaseDto<ID>, Entity extends BaseEntity<ID>, ID extends Serializable>
		implements IService<Dto, Entity, ID> {

	/**
	 * Method to quickly map to a BaseEntity from its id
	 * @param <T>
	 * @param <ID1>
	 * @param repository T's repository
	 * @param id         T's id
	 * @param class1     T's class
	 * @return T
	 * @throws ResourceNotFoundException if no T is found for the id
	 */
	protected <T extends BaseEntity<ID1>, ID1 extends Serializable> T idToObj(JpaRepository<T, ID1> repository, ID1 id,
			Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(id)) {
			return null;
		}
		return repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(class1, id));
	};
	
	/**
	 * Method to quickly map to a {@link Set} of BaseEntity from a {@link Set} of
	 * its id
	 * @param <T>
	 * @param <ID1>
	 * @param repository T's repository
	 * @param ids        {@link Set} of T's id
	 * @param class1     T's class
	 * @return {@link Set} of T
	 * @throws ResourceNotFoundException
	 */
	protected <T extends BaseEntity<ID1>, ID1 extends Serializable> Set<T> idToObj(JpaRepository<T, ID1> repository,
			Set<ID1> ids, Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		Set<T> ts = new HashSet<T>();
		for (ID1 id : ids) {
			if (Objects.isNull(ids)) {
				continue;
			}
			ts.add(idToObj(repository, id, class1));
		}
		return ts;
	}
}
