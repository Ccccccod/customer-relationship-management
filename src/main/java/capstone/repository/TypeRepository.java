/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Source;
import capstone.entity.Type;

/**
 * Repository for {@link Source}
 * @author Tuna
 *
 */
@Repository
public interface TypeRepository extends NamedJpaRepository<Type, Long>{
	
}
