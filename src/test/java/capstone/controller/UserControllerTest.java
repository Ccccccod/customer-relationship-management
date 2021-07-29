/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.dto.response.UserResponse;
import capstone.entity.User;
import capstone.repository.UserRepository;

/**
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
		return Arrays.asList(User.builder()
				.name("Minhkien1")
				.password("Minhkien1@")
				.email("sdfdsfsdcsdasd@gmail.com")
				.build() ,
				User.builder()
						.name("Anhtuuuu2")
						.password("Minhkien1@")
						.email("fdsfdf@gmail.com")
						.build() ,
						User.builder()
								.name("MinhDucccc3")
								.password("Minhkien1@")
								.email("cjfhs@gmail.com")
								.build());
	}

	@Override
	protected User resource() {
		return User.builder().id(1L).name("Manhcuong4").password("Minhkien1@").email("xcdfd@gmail.com").build();
	}
	@Test
	@Override
	public void testGetById() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");
	}
	@Test
	@Override
	public void testGetAll() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");
	}
	@Test
	@Override
	public void testCreateUpdate() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");
	}

}
