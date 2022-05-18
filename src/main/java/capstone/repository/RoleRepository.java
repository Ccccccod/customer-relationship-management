/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Role;

/**
 * RoleRepository
 * @author Tuna
 */
@Repository
public interface RoleRepository extends NamedJpaRepository<Role, Long>, BaseRepository<Role, Long> {
	
	/**
	 * Find role by name
	 * @param name
	 * @return
	 */
	List<Role> findByName(String name);

}
