/**
 * 
 */
package capstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.PermissionAction;
import capstone.entity.PermissionFunction;
import capstone.entity.PermissionFunctionAction;

/**
 * @author Tuna
 *
 */
@Repository
public interface PermissionFunctionActionRepository extends JpaRepository<PermissionFunctionAction, Long>{
	
	Optional<PermissionFunctionAction> findByPermissionFunctionAndPermissionAction(
			PermissionFunction permissionFunction, PermissionAction permissionAction);

}
