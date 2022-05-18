/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Login Request
 * @author Tuna
 *
 */

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {

	@NotNull
	private String username;

	@NotNull
	private String password;
}
