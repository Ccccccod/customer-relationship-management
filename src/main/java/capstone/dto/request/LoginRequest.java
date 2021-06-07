/**
 * 
 */
package capstone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
	private String username;
	private String password;
}
