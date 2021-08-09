/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.OpportunityPhase;
import capstone.repository.OpportunityPhaseRepository;

/**
 * OpportunityPhaseController
 * Gia đoạn cơ hội Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/opportunityPhase")
public class OpportunityPhaseController
		extends AbstractSimpleCRUDController<OpportunityPhase, OpportunityPhaseRepository, Long>
		implements IReadNameController<OpportunityPhase, OpportunityPhaseRepository, Long> {

	@Override
	protected Class<OpportunityPhase> entityClass() {
		return OpportunityPhase.class;
	}

}
