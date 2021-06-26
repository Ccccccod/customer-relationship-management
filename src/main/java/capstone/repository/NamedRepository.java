/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.entity.NamedEntity;

/**
 * @author Tuna
 *
 */
public interface NamedRepository<T extends NamedEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
	
	Boolean existsByName(String name);
	
	Optional<T> findByName(String name);

}
