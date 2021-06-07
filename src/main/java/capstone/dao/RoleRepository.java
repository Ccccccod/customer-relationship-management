/**
 * 
 */
package capstone.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import capstone.entity.Role;

/**
 * @author Tuna
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>{
	
	/**
	 * Find role by name
	 * @param name
	 * @return
	 */
	Optional<Role> findByName(String name);

}
