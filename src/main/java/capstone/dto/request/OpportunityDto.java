/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	@javax.validation.constraints.Min(value = 0)
	@javax.validation.constraints.Max(value = 100)
	private Integer successRate;

	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate expectedEndDate;

	/**
	 * Doanh số kỳ vọng
	 */
	@PositiveOrZero
	private Long expectedTurnOver;
	
	/**
	 * Nguồn gốc
	 */
	private Long sourceId;
	
	/**
	 * Thông tin hàng hóa
	 */
	private Set<ProductInfoDto> productInfoDtos;

	/**
	 * @param id
	 * @param name
	 * @param customerId
	 * @param contactId
	 * @param moneyAmount
	 * @param opportunityPhaseId
	 * @param successRate
	 * @param expectedEndDate
	 * @param expectedTurnOver
	 * @param sourceId
	 * @param productInfoDtos
	 */
	@Builder
	OpportunityDto(Long id, String name, Long customerId, Long contactId, Long moneyAmount, Long opportunityPhaseId,
			Integer successRate, LocalDate expectedEndDate, Long expectedTurnOver, Long sourceId,
			Set<ProductInfoDto> productInfoDtos) {
		super(id, name);
		this.customerId = customerId;
		this.contactId = contactId;
		this.moneyAmount = moneyAmount;
		this.opportunityPhaseId = opportunityPhaseId;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.expectedTurnOver = expectedTurnOver;
		this.sourceId = sourceId;
		this.productInfoDtos = productInfoDtos;
	}

}
