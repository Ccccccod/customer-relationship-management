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
 * User Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserDto extends BaseDto<Long> {

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
	
	private Set<Long> roleIds;

}
