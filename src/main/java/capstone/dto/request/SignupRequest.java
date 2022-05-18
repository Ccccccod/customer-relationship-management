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
import lombok.experimental.SuperBuilder;

/**
 * Sign up request
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {

	@JsonAlias({ "name" })
	@NotNull
	@Username
	private String username;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Password
	private String password;

	private Set<String> roles;
}
