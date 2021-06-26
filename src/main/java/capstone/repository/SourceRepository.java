/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Source;

/**
 * Repository for {@link Source}
 * @author Tuna
 *
 */
@Repository
public interface SourceRepository extends JpaRepository<Source, Long>{

}
