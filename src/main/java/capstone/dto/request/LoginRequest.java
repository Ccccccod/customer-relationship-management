/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Login Request
 * @author Tuna
 *
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {

	@NotNull(message = "Please provide username")
	@javax.validation.constraints.Pattern(regexp = Constant.Validation.USERNAME_REGEX, message = "Username is not valid")
	private String username;

	@NotNull(message = "Please provide password")
	@Password
	private String password;
}
