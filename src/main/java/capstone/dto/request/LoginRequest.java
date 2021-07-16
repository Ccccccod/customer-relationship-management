/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import capstone.dto.validatation.annotation.Password;
import capstone.dto.validatation.annotation.Username;
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
	@Username
	private String username;

	@NotNull(message = "Please provide password")
	@Password
	private String password;
}
