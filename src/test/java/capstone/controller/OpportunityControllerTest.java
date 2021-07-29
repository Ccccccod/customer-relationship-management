/**
 * 
 */
package capstone.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Opportunity;
import capstone.entity.Product;
import capstone.repository.OpportunityRepository;

/**
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
		return Opportunity.builder().id(1L).name("co hoi 4").expectedEndDate(LocalDate.of(2021, 4, 20)).build();
	}

	@Override
	@Test
	public void testCreateUpdate() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");

	}
}
