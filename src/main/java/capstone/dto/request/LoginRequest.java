/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

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
	private String username;

	@NotNull(message = "Please provide password")
	private String password;
}
