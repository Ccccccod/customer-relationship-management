/**
 * 
 */
package capstone.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositoried
 * @author Tuna
 *
 */
public interface Repositoried<T extends JpaRepository<?, ?>> {
	
	T getRepository();

}
