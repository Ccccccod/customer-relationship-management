/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Career;
import capstone.entity.Field;

/**
 * Repository for {@link Career}
 * @author Tuna
 */
@Repository
public interface CareerRepository extends NamedJpaRepository<Career, Long> {
	
	List<IdAndNameAndField> findIdNameFieldAllBy();
	
	interface IdAndNameAndField {
		Long getId();
		String getName();
		Field getField();
	}

}
