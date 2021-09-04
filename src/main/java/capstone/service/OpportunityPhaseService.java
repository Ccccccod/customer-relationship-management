/**
 * 
 */
package capstone.service;

import capstone.entity.OpportunityPhase;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.OpportunityPhaseRepository;

/**
 * OpportunityPhaseService
 * @author DELL
 *
 */
public class OpportunityPhaseService extends AbstractService<OpportunityPhase, OpportunityPhase, OpportunityPhase, OpportunityPhase, OpportunityPhaseRepository, Long> {

	@Override
	protected Class<OpportunityPhase> entityClass() {
		return OpportunityPhase.class;
	}

	@Override
	protected OpportunityPhase entityToResponse(OpportunityPhase entity) {
		return entity;
	}

	@Override
	protected OpportunityPhase createDtoToEntity(OpportunityPhase d, OpportunityPhase entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected OpportunityPhase updateDtoToEntity(OpportunityPhase updateDto, OpportunityPhase entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
