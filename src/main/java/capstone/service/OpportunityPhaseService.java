/**
 * 
 */
package capstone.service;

import java.util.List;

import org.hibernate.Session;
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
	
	@Override
	public List<?> getAllName() throws ResourceNotFoundException {
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.repository.findIdNameSuccessRateAllBy();
		} finally {
			disableDeletedFilter(session);
		}
	}

}
