/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Field;

/**
 * Repository for {@link Field}
 * @author Tuna
 *
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long>, NamedJpaRepository<Field, Long> {

}
