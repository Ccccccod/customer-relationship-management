/**
 * 
 */
package capstone.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.OpportunityPhaseRepository;
import capstone.repository.SourceRepository;
import capstone.service.AbstractService;

/**
 * OpportunityController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController extends AbstractDtoEntityController<OpportunityDto, Opportunity, Long> {
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected ContactRepository contactRepository;

	@Autowired
	protected OpportunityPhaseRepository opportunityPhaseRepository;
	
	@Autowired
	protected SourceRepository sourceRepository;

	@Override
	protected Opportunity dtoToEntity(OpportunityDto dto) throws ResourceNotFoundException {
		Opportunity opportunity = Opportunity.builder()
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.contact(AbstractService.findEntityById(contactRepository, dto.getContactId(), Contact.class))
				.moneyAmount(dto.getMoneyAmount())
				.opportunityPhase(AbstractService.findEntityById(opportunityPhaseRepository, dto.getOpportunityPhaseId(), OpportunityPhase.class))
				.successRate(dto.getSuccessRate())
				.expectedEndDate(dto.getExpectedEndDate())
				.expectedTurnOver(dto.getExpectedTurnOver())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.build();
		if (Objects.isNull(opportunity.getExpectedTurnOver())) {
			opportunity.setExpectedTurnOver(opportunity.getMoneyAmount() * opportunity.getSuccessRate());
		}
		return opportunity;
	}

}
