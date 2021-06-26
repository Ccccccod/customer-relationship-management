/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Classification;

/**
 * Repository for {@link Classification}
 * @author Tuna
 *
 */
@Repository
public interface ClassificationRepository extends NamedRepository<Classification, Long> {

}
