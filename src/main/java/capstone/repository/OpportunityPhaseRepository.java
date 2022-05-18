/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.OpportunityPhase;
import capstone.model.IdAndName;

/**
 * OpportunityPhaseRepository
 * @author Tuna
 */
@Repository
public interface OpportunityPhaseRepository
		extends NamedJpaRepository<OpportunityPhase, Long>, BaseRepository<OpportunityPhase, Long> {

	List<IdAndNameAndSuccessRate<Long>> findIdNameSuccessRateAllBy();

	public interface IdAndNameAndSuccessRate<ID extends Serializable> extends IdAndName<ID> {
		ID getId();

		String getName();

		Integer getSuccessRate();
	}

}
