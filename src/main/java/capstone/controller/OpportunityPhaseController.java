/**
 * 
 */
package capstone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class OpportunityPhaseController {

	@GetMapping("/name")
	public ResponseEntity<?> getAllName() {
		return ResponseEntity.ok(OpportunityPhase.values());
	}

}
