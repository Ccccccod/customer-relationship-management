/**
 * 
 */
package capstone.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import capstone.entity.Role;
import capstone.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User Response
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponse extends BaseResponse<Long> {
	
	private String username;
	
	private String email;
	
	private Set<Role> roles;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param username
	 * @param email
	 * @param roles
	 */
	@Builder
	public UserResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String username,
			String email, Set<Role> roles) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}
