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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto extends BaseDto<Long> {

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
	
	private Set<Long> roleIds;

}
