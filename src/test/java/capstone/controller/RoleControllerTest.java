/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import capstone.dto.request.RoleDto;
import capstone.entity.Product;
import capstone.entity.Role;
import capstone.repository.RoleRepository;

/**
 * @author DELL
 *
 */
public class RoleControllerTest
		extends AbstractDtoEntityControllerTest<RoleDto, Role, RoleRepository, RoleController, Long> {

	@Override
	protected String url() {
		return "/api/role";
	}

	@Override
	protected List<Role> resources() {
		return Arrays.asList(Role.builder()
				.name("quan tri")
				.build() ,
				Role.builder()
						.name("thanh vien")
						
						.build() ,
						Role.builder()
								.name("he thong")
								.build());
	}

	@Override
	protected Role resource() {
		return Role.builder().id(1L).name("thanh vien").build();
	}

	@Override
	@Test
	public void testCreateUpdate() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");

	}
}
