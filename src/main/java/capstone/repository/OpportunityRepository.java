/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;

/**
 * OpportunityRepository
 * @author Tuna
 */
@Repository
public interface OpportunityRepository extends NamedJpaRepository<Opportunity, Long>, BaseRepository<Opportunity, Long>,
		CodedRepository<Opportunity, Long> {

	List<Opportunity> findByExpectedEndDateBetween(LocalDate from, LocalDate to);
	
	List<Opportunity> findByOpportunityPhaseAndExpectedEndDateBetween(OpportunityPhase opportunityPhase, LocalDate from,
			LocalDate to);

}
