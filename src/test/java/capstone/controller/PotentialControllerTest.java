/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.PotentialDto;
import capstone.entity.Potential;
import capstone.repository.PotentialRepository;
import capstone.service.PotentialService;

/**
 * PotentialControllerTest
 * @author Tuna
 */
public class PotentialControllerTest extends
		CRUDControllerTest<PotentialDto, PotentialDto, Potential, Potential, PotentialRepository, PotentialService, PotentialController, Long> {

	@Override
	protected String url() {
		return "/api/potential";
	}

	@Override
	protected List<Potential> resources() {
		return Arrays.asList( //
				Potential.builder() //
						.name("Tiem nang 1") //
						.facebook("facebook") //
						.lastName("a") //
						.build(), //
				Potential.builder() //
						.name("Tiem nang 2")
						.bankAccount("0123456789")
						.build());
	}

	@Override
	protected Potential resource() {
		return Potential.builder() //
				.name("Tiem nang 1") //
				.facebook("facebook") //
				.lastName("a") //
				.build();
	}

}
