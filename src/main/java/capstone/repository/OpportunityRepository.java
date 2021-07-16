/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Opportunity;

/**
 * @author Tuna
 *
 */
@Repository
public interface OpportunityRepository extends NamedJpaRepository<Opportunity, Long>{

}
