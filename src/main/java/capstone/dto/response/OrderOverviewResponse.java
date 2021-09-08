/**
 * 
 */
package capstone.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * OrderOverviewResponse
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
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
	
	/**
	 * Từ ngày
	 */
	private LocalDate from;

	/**
	 * Đến ngày
	 */
	private LocalDate to;
	
}
