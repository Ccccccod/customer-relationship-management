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
 * Response DTO that contains only a message
 */
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
	private String message;
}
