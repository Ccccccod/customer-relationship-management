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

/**
 * {@link JpaRepository} for {@link NamedEntity}
 * @author Tuna
 */
@NoRepositoryBean
public interface NamedJpaRepository<T extends NamedEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
	
	/**
	 * Exists by name
	 * @param name
	 * @return
	 */
	Boolean existsByName(String name);
	
	/**
	 * Find by name
	 * @param name
	 * @return
	 */
	Optional<T> findByName(String name);
	
	List<T> findByNameIgnoreCase(String name);
	
	List<T> findByNameContaining(String name);

	List<T> findByNameNot(String name);

}
