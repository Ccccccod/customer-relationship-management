/**
 * 
 */
package capstone.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.DateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Cơ hội dto
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpportunityDto extends NamedDto<Long> {
	
	/**
	 * Khách hàng
	 */
	private Long customerId;
	
	/**
	 * Liên hệ
	 */
	private Long contactId;
	
	/**
	 * Số tiền
	 */
	@NotNull(message = "moneyAmount must not be null")
	@PositiveOrZero(message = "moneyAmount must be positive or zero")
	private Long moneyAmount;

	/**
	 * Gian đoạn
	 */
	@NotNull(message = "opportunityPhaseId must not be null")
	private Long opportunityPhaseId;

	/**
	 * Tỷ lệ thành công
	 */
	@NotNull(message = "successRate must not be null")
	@javax.validation.constraints.Min(value = 0, message = "successRate must not be less than 0")
	@javax.validation.constraints.Max(value = 100, message = "successRate must not be greater than 100")
	private Integer successRate;

	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@NotNull(message = "expectedEndDate must not be null")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date expectedEndDate;

	/**
	 * Doanh số kỳ vọng
	 */
	@PositiveOrZero(message = "expectedTurnOver must be positive or zero")
	private Long expectedTurnOver;
	
	/**
	 * Nguồn gốc
	 */
	private Long sourceId;

}
