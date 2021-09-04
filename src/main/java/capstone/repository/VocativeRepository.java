/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Vocative;

/**
 * VocativeRepository
 * @author Tuna
 */
@Repository
public interface VocativeRepository extends NamedJpaRepository<Vocative, Long> {

}
