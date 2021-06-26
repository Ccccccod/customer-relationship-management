/**
 * 
 */
package capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tuna
 * Response DTO that contains only a message
 */
@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
	private String message;
}
