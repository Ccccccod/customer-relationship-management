/**
 * 
 */
package capstone.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import capstone.entity.User;

/**
 * @author Tuna
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	/**
	 * Find user by name
	 * @param name
	 * @return
	 */
	Optional<User> findByName(String name);
	
	/**
	 * Check exist by name
	 * @param name
	 * @return
	 */
	Boolean existsByName(String name);
	
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
