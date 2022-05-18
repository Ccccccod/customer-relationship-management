/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.OpportunityPhase;
import capstone.service.OpportunityPhaseService;

/**
 * OpportunityPhaseController 
 * Gia đoạn cơ hội Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/opportunityPhase")
public class OpportunityPhaseController
		implements IReadNameController<OpportunityPhase, OpportunityPhaseService, Long> {
	
	@Autowired
	private OpportunityPhaseService opportunityPhaseService;

	@Override
	public OpportunityPhaseService getService() {
		return opportunityPhaseService;
	}

}
