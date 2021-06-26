/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Source;

/**
 * Repository for {@link Source}
 * @author Tuna
 *
 */
@Repository
public interface SourceRepository extends NamedRepository<Source, Long>{

}
