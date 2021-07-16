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
	@NotNull
	@PositiveOrZero
	private Long moneyAmount;

	/**
	 * Gian đoạn
	 */
	@NotNull
	private Long opportunityPhaseId;

	/**
	 * Tỷ lệ thành công
	 */
	@NotNull
	@javax.validation.constraints.Min(0)
	@javax.validation.constraints.Max(100)
	private Integer successRate;

	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@NotNull
	@JsonDeserialize(using = DateDeserializer.class)
	private Date expectedEndDate;

	/**
	 * Doanh số kỳ vọng
	 */
	@NotNull
	@PositiveOrZero
	private Long expectedTurnOver;
	
	/**
	 * Nguồn gốc
	 */
	private Long sourceId;

}
