/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.PermissionFunction;

/**
 * @author Tuna
 *
 */
@Repository
public interface PermissionFunctionRepository extends NamedJpaRepository<PermissionFunction, Long>{

}
