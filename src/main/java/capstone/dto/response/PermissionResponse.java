/**
 * 
 */
package capstone.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
public class PermissionResponse {
	
	List<PermissionFunctionResponse> permissionFunctionResponses;

}
