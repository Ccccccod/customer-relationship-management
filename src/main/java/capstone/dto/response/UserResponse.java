/**
 * 
 */
package capstone.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.UserSerializer;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.model.Identifiable;
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
//@AllArgsConstructor
public class UserResponse implements Identifiable<Long> {
	
	private Long id;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	@JsonSerialize(using = UserSerializer.class)
	private User createdBy;

	@JsonSerialize(using = UserSerializer.class)
	private User updatedBy;
	
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
	public UserResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String username, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}
