/**
 * 
 */
package capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class PermissionActionResponse {
	
	private Long id;
	
	private String name;
	
	private Boolean allowed;

}
