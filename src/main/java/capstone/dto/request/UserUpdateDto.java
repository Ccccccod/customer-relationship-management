/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * UserUpdateDto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto extends BaseDto<Long> {

	@JsonAlias({ "name" })
	@NotNull(message = "Please provide username")
	@javax.validation.constraints.Pattern(regexp = Constant.Validation.USERNAME_REGEX, message = "Username is not valid")
	private String username;

	@NotNull(message = "Please provide email")
	@Email
	private String email;
	
	private Set<Long> roleIds;

}
