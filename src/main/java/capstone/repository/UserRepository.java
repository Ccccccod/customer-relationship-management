/**
 * 
 */
package capstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import capstone.entity.User;

/**
 * @author Tuna
 *
 */
@Repository
public interface UserRepository extends NamedJpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
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
}
