/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import capstone.dto.validatation.annotation.Email;
import capstone.dto.validatation.annotation.Password;
import capstone.dto.validatation.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Sign up request
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {

	@JsonAlias({ "name" })
	@NotNull(message = "Please provide username")
	@Username
	private String username;

	@NotNull(message = "Please provide email")
	@Email
	private String email;

	@NotNull(message = "Please provide password")
	@Password
	private String password;

	private Set<String> roles;
}
