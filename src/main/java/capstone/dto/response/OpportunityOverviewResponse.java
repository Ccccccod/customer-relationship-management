/**
 * 
 */
package capstone.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * OpportunityOverviewResponse
 * @author Tuna
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OpportunityOverviewResponse {
	
	/**
	 * Số lượng cơ hội đang thực hiện
	 */
	private Integer numberOfOportunitiesInProgress;
	
	/**
	 * Doanh số đã thực hiện.
	 */
	private Long doneTurnOver;

	/**
	 * Doanh số kỳ vọng
	 */
	private Long expectedTurnOver;
	
	/**
	 * Tỷ lệ chiến thắng cơ hội
	 */
	private Double opportunityWinRate;
	
	/**
	 * Từ ngày
	 */
	private LocalDate from;

	/**
	 * Đến ngày
	 */
	private LocalDate to;

}
