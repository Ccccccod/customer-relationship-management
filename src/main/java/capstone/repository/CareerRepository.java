/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Career;
import capstone.entity.Field;
import capstone.model.IdAndName;

/**
 * Repository for {@link Career}
 * @author Tuna
 */
@Repository
public interface CareerRepository extends NamedJpaRepository<Career, Long>, BaseRepository<Career, Long> {
	
	List<IdAndNameAndField> findIdNameFieldAllBy();
	
	interface IdAndNameAndField extends IdAndName<Long> {
		Long getId();
		String getName();
		Field getField();
	}

}
