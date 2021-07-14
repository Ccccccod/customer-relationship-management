/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.PermissionAction;

/**
 * @author Tuna
 *
 */
@Repository
public interface PermissionActionRepository extends NamedJpaRepository<PermissionAction, Long>{

}
