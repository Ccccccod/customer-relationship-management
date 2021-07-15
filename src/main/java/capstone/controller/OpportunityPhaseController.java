/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.OpportunityPhase;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/opportunityPhase")
public class OpportunityPhaseController extends AbstractSimpleCRUDController<OpportunityPhase, Long> {

}
