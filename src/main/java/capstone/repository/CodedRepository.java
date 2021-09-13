/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import capstone.model.Coded;
import capstone.model.Identifiable;

/**
 * Coded Repository
 * @author Tuna
 */
@NoRepositoryBean
public interface CodedRepository<T extends Identifiable<ID> & Coded, ID extends Serializable>
		extends JpaRepository<T, ID> {
	
	Boolean existsByCode(String code);
	
	Optional<T> findFirstByCode(String code);
	
	List<T> findByCode(String code);

}
