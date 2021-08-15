/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Opportunity;

/**
 * OpportunityRepository
 * @author Tuna
 *
 */
@Repository
public interface OpportunityRepository extends NamedJpaRepository<Opportunity, Long> {
	
	List<Opportunity> findByExpectedEndDateBetween(LocalDate from, LocalDate to);

}
