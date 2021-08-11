/**
 * 
 */
package capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * OverviewResponse
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class OverviewResponse {
	
	private Integer quantity;
	
	private Long turnOver;
	
	private Integer recordedQuantity;
	
	private Long recordedTurnOver;

}
