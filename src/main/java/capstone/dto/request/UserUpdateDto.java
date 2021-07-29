/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import capstone.dto.validatation.annotation.Email;
import capstone.dto.validatation.annotation.Username;
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
	@NotNull
	@Username
	private String username;

	@NotNull
	@Email
	private String email;
	
	private Set<Long> roleIds;

}
