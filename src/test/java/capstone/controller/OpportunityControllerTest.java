/**
 * 
 */
package capstone.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import capstone.common.enums.OpportunityPhase;
import capstone.dto.request.OpportunityDto;
import capstone.entity.Opportunity;
import capstone.repository.OpportunityRepository;

/**
 * OpportunityControllerTest
 * @author DELL
 *
 */
public class OpportunityControllerTest extends
		AbstractDtoEntityControllerTest<OpportunityDto, Opportunity, OpportunityRepository, OpportunityController, Long> {

	@Override
	protected String url() {
		return "/api/opportunity";
	}

	@Override
	protected List<Opportunity> resources() {
		return Arrays.asList(Opportunity.builder()
				.name("co hoi 1")
				.build() ,
				Opportunity.builder()
						.name("co hoi 2")
						.build() ,
						Opportunity.builder()
								.name("co hoi 3")
								.build());
	}

	@Override
	protected Opportunity resource() {
		return Opportunity.builder()
				.id(1L)
				.name("co hoi 4")
				.opportunityPhase(OpportunityPhase.BEGINNING)
				.moneyAmount(1000L)
				.successRate(90)
				.expectedEndDate(LocalDate.of(2021, 4, 20))
				.build();
	}

	@Override
	protected OpportunityDto createResource() {
		return OpportunityDto.builder()
				.name(resource().getName())
				.opportunityPhaseId(resource().getOpportunityPhase())
				.moneyAmount(resource().getMoneyAmount())
				.successRate(resource().getSuccessRate())
				.expectedEndDate(resource().getExpectedEndDate())
				.build();
	}

}
