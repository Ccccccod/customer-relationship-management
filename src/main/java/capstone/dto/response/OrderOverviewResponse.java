/**
 * 
 */
package capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * OrderOverviewResponse
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class OrderOverviewResponse {
	
	/**
	 * Số lượng đơn hàng
	 */
	private Integer quantity;
	
	/**
	 * Doanh số đặt hàng
	 */
	private Long turnOver;
	
	/**
	 * Số lượng đã ghi nhận
	 */
	private Integer recordedQuantity;
	
	/**
	 * Doanh số đã ghi nhận
	 */
	private Long recordedTurnOver;

}
