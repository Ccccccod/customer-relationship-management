/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {

	private String username;
	private String email;
	private String password;
	private Set<String> role;
}
