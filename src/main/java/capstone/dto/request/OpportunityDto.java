/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.entity.OpportunityPhase;
import capstone.model.Coded;
import capstone.model.Named;
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
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpportunityDto extends BaseDto<Long> implements Coded, Named {

	/**
	 * Mã cơ hội
	 */
	private String code;

	/**
	 * Tên cơ hội
	 */
	private String name;
	
	/**
	 * Khách hàng
	 */
	private Long customerId;
	
	/**
	 * Liên hệ
	 */
	private Long contactId;

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
	 * Nguồn gốc
	 */
	private Long sourceId;
	
	/**
	 * Thông tin hàng hóa
	 */
	private Set<ProductInfoDto> productInfoDtos;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param customerId
	 * @param contactId
	 * @param opportunityPhaseId
	 * @param successRate
	 * @param expectedEndDate
	 * @param sourceId
	 * @param productInfoDtos
	 */
	
	@Builder(toBuilder = true)
	public OpportunityDto(Long id, String code, String name, Long customerId, Long contactId,
			@NotNull Long opportunityPhaseId, @NotNull @Min(0) @Max(100) Integer successRate,
			@NotNull LocalDate expectedEndDate, Long sourceId, Set<ProductInfoDto> productInfoDtos) {
		super(id);
		this.code = code;
		this.name = name;
		this.customerId = customerId;
		this.contactId = contactId;
		this.opportunityPhaseId = opportunityPhaseId;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.sourceId = sourceId;
		this.productInfoDtos = productInfoDtos;
	}

}
