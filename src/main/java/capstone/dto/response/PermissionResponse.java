/**
 * 
 */
package capstone.dto.response;

import java.util.List;

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
public class PermissionResponse {
	
	List<PermissionFunctionResponse> permissionFunctionResponses;

}
