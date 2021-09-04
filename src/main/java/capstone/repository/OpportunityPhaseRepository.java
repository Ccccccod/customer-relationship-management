/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.OpportunityPhase;

/**
 * OpportunityPhaseRepository
 * @author Tuna
 */
@Repository
public interface OpportunityPhaseRepository extends NamedJpaRepository<OpportunityPhase, Long> {

}
