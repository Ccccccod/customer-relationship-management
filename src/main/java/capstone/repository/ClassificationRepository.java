/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Classification;

/**
 * Repository for {@link Classification}
 * @author Tuna
 *
 */
@Repository
public interface ClassificationRepository
		extends JpaRepository<Classification, Long>, NamedJpaRepository<Classification, Long> {

}
