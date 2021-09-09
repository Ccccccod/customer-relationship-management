/**
 * 
 */
package capstone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import capstone.entity.User;

/**
 * UserRepository
 * @author Tuna
 */
@Repository
public interface UserRepository extends NamedJpaRepository<User, Long>, BaseRepository<User, Long> {
	
	/**
	 * Check exist by email
	 * @param email
	 * @return
	 */
	Boolean existsByEmail(String email);
	
	/**
	 * Find user by email
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Find by name
	 * @param name
	 * @return
	 */
	List<User> findByUsername(String username);
	
	/**
	 * Exists by name
	 * @param name
	 * @return
	 */
	Boolean existsByUsername(String username);
}
