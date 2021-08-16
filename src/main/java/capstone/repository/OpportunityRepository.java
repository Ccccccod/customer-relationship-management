/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.common.enums.OpportunityPhase;
import capstone.entity.Opportunity;

/**
 * OpportunityRepository
 * @author Tuna
 *
 */
@Repository
public interface OpportunityRepository extends NamedJpaRepository<Opportunity, Long> {
	
	List<Opportunity> findByExpectedEndDateBetween(LocalDate from, LocalDate to);
	
	List<Opportunity> findByOpportunityPhaseAndExpectedEndDateBetween(OpportunityPhase opportunityPhase, LocalDate from,
			LocalDate to);

}
