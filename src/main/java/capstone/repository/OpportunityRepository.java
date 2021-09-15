/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.model.IdAndName;

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
	
	Optional<CustomerOnly> findCustomerIdAndNameById(Long id);

	public interface CustomerOnly {
		IdAndName<Long> getCustomer();
	}
	
	Optional<ContactOnly> findContactIdAndNameById(Long id);
	
	public interface ContactOnly {
		IdAndName<Long> getContact();
	}

}
