/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.OpportunityPhase;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.OpportunityPhaseRepository;
import capstone.service.iservice.INamedService;

/**
 * OpportunityPhaseService
 * @author DELL
 * @author tuna
 */
@Service
public class OpportunityPhaseService extends
		AbstractService<OpportunityPhase, OpportunityPhase, OpportunityPhase, OpportunityPhase, OpportunityPhaseRepository, Long>
		implements INamedService<OpportunityPhase, OpportunityPhaseRepository, Long> {

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
