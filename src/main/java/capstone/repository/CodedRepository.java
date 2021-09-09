/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import capstone.entity.BaseEntity;
import capstone.model.Coded;

/**
 * Coded Repository
 * @author Tuna
 */
@NoRepositoryBean
public interface CodedRepository<T extends BaseEntity<ID> & Coded, ID extends Serializable> {
	
	Boolean existsByCode(String code);
	
	Optional<T> findFirstByCode(String code);
	
	List<T> findByCode(String code);

}
