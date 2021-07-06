/**
 * 
 */
package capstone.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.entity.BaseEntity;
import capstone.exception.ResourceNotFoundException;
import capstone.utils.DtoUtils;

/**
 * Abstract Service
 * @author Tuna
 * @param <Dto>
 * @param <Entity>
 * @param <ID>
 */
public abstract class AbstractService {

	/**
	 * Method to quickly map to a BaseEntity from its id
	 * @param <T>
	 * @param <ID>
	 * @param repository T's repository
	 * @param id         T's id
	 * @param class1     T's class
	 * @return T
	 * @throws ResourceNotFoundException if no T is found for the id
	 */
	protected <T extends BaseEntity<ID>, ID extends Serializable> T idToObj(JpaRepository<T, ID> repository, ID id,
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
	 * @param <ID>
	 * @param repository T's repository
	 * @param ids        {@link Set} of T's id
	 * @param class1     T's class
	 * @return {@link Set} of T
	 * @throws ResourceNotFoundException
	 */
	protected <T extends BaseEntity<ID>, ID extends Serializable> Set<T> idToObj(JpaRepository<T, ID> repository,
			Set<ID> ids, Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		Set<T> ts = new LinkedHashSet<>();
		for (ID id : ids) {
			if (Objects.isNull(ids)) {
				continue;
			}
			ts.add(idToObj(repository, id, class1));
		}
		return ts;
	}
	
	/**
	 * Method to quickly map to a {@link List} of BaseEntity from a {@link List} of
	 * its id
	 * @param <T>
	 * @param <ID>
	 * @param repository T's repository
	 * @param ids        {@link List} of T's id
	 * @param class1     T's class
	 * @return {@link List} of T
	 * @throws ResourceNotFoundException
	 */
	protected <T extends BaseEntity<ID>, ID extends Serializable> List<T> idToObj(JpaRepository<T, ID> repository,
			List<ID> ids, Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		List<T> ts = new LinkedList<>();
		for (ID id : ids) {
			if (Objects.isNull(ids)) {
				continue;
			}
			ts.add(idToObj(repository, id, class1));
		}
		return ts;
	}
	
	/**
	 * Method to quickly map to a {@link Collection} of BaseEntity from a {@link Collection} of
	 * its id
	 * @param <T>
	 * @param <ID1>
	 * @param repository T's repository
	 * @param ids        {@link Collection} of T's id
	 * @param class1     T's class
	 * @return {@link Collection} of T
	 * @throws ResourceNotFoundException
	 */
	protected <T extends BaseEntity<ID1>, ID1 extends Serializable> Collection<T> idToObj(JpaRepository<T, ID1> repository,
			Collection<ID1> ids, Class<T> class1) throws ResourceNotFoundException {
		return idToObj(repository, ids, class1);
	}
	
}
