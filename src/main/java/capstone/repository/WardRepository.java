/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Ward;

/**
 * WardRepository
 * @author Tuna
 */
@Repository
public interface WardRepository extends NamedJpaRepository<Ward, Long> {

}
