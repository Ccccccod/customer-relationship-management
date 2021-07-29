/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.dto.response.UserResponse;
import capstone.entity.User;
import capstone.repository.UserRepository;

/**
 * UserControllerTest
 * @author DELL
 *
 */
public class UserControllerTest extends
		AbstractCRUDControllerTest<UserDto, UserUpdateDto, UserResponse, User, UserRepository, UserController, Long> {

	@Override
	protected String url() {
		return "/api/user";
	}

	@Override
	protected List<User> resources() {
		return Arrays.asList(
				User.builder()
						.name("Minhkien15663")
						.password("Minhkien1@")
						.email("sdfdsfsdcsdasd@gmail.com")
						.build(),
				User.builder()
						.name("Anhtuuuu2")
						.password("Minhkien1@")
						.email("fdsfdf@gmail.com")
						.build(),
				User.builder()
						.name("MinhDucccc3")
						.password("Minhkien1@")
						.email("cjfhs@gmail.com")
						.build()
		);
	}

	@Override
	protected User resource() {
		return User.builder()
				.id(1L)
				.name("Manhcuong8964")
				.password("Minhkien1@")
				.email("xcdfdddo@gmail.com")
				.build();
	}

	@Override
	protected UserDto createResource() {
		return UserDto.builder()
				.username(resource().getName())
				.password(resource().getPassword())
				.email(resource().getEmail())
				.build();
	}

}
