/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Field;

/**
 * Repository for {@link Field}
 * @author Tuna
 *
 */
@Repository
public interface FieldRepository extends NamedRepository<Field, Long> {

}
