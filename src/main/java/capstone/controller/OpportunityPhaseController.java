/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.common.enums.OpportunityPhase;

/**
 * OpportunityPhaseController
 * Gia đoạn cơ hội Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/opportunityPhase")
public class OpportunityPhaseController implements EnumController<OpportunityPhase> {

	@Override
	public Class<OpportunityPhase> getEnumClass() {
		return OpportunityPhase.class;
	}

}
