/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import capstone.dto.validatation.annotation.ChangePassword;
import capstone.dto.validatation.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Change Password Request
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor

@NotNull
@ChangePassword
public class ChangePasswordRequest {
	
	@NotNull
	private String oldPassword;

	@NotNull
	@Password
	private String newPassword;

}
