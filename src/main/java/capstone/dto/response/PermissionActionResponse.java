/**
 * 
 */
package capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Tuna
 *
 */

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
public class PermissionActionResponse {
	
	private Long id;
	
	private String name;
	
	private Boolean allowed;

}
