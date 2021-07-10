/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Email;
import capstone.dto.validatation.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * UserDto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserDto extends BaseDto<Long> {

	@JsonAlias({ "name" })
	@NotNull(message = "Please provide username")
	@javax.validation.constraints.Pattern(regexp = Constant.Validation.USERNAME_REGEX, message = "Username is not valid")
	private String username;

	@NotNull(message = "Please provide email")
	@Email
	private String email;

	@NotNull(message = "Please provide password")
	@Password
	private String password;
	
	private Set<Long> roleIds;

}
