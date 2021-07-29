/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.RoleDto;
import capstone.entity.Role;
import capstone.repository.RoleRepository;

/**
 * RoleControllerTest
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
	protected RoleDto createResource() {
		return RoleDto.builder()
				.name(resource().getName())
				.build();
	}
}
