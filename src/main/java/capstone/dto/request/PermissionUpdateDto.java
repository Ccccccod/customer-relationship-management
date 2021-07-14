/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

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
public class PermissionUpdateDto {
	
	@NotNull
	private Set<Long> permissionFunctionActionIds;

}
