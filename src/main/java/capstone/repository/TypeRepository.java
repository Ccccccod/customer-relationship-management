/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Source;
import capstone.entity.Type;

/**
 * Repository for {@link Source}
 * @author Tuna
 *
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{
	
}
