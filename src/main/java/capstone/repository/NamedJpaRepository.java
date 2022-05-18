/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import capstone.entity.NamedEntity;
import capstone.model.IdAndName;
import capstone.model.Identifiable;
import capstone.model.Named;

/**
 * {@link JpaRepository} for {@link NamedEntity}
 * @author Tuna
 */
@NoRepositoryBean
public interface NamedJpaRepository<T extends Identifiable<ID> & Named, ID extends Serializable> extends JpaRepository<T, ID> {
	
	/**
	 * Exists by name
	 * @param name
	 * @return
	 */
	Boolean existsByName(String name);
	
	/**
	 * Find top 1 by name
	 * @param name
	 * @return
	 */
	Optional<T> findFirstByName(String name);
	
	/**
	 * Find by name
	 * @param name
	 * @return
	 */
	List<T> findByName(String name);
	
	List<IdAndName<ID>> findIdNameAllBy();
	
//	/**
//	 * Find by name order by id
//	 * @param name
//	 * @return
//	 */
//	List<T> findByNameOrderByIdAsc(String name);
//	
//	List<T> findByNameIgnoreCase(String name);
//	
//	List<T> findByNameContaining(String name);
//
//	List<T> findByNameNot(String name);

}
