/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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
 * OrderDto
 * Đơn hàng Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends CodedNamedDto<Long> {
	
	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate orderDate;
	
	private Long customerId;
	
	private Long contactId;
 
	private Long opportunityId;

	@NotNull
	private Long orderValue;
	
	private Long liquidationValue;

	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate liquidationDeadline;

	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate deliveryDeadline;
	
	/**
	 * Tình trạng thanh toán
	 */
	@NotNull
	private Boolean paid;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param orderDate
	 * @param customerId
	 * @param contactId
	 * @param opportunityId
	 * @param orderValue
	 * @param liquidationValue
	 * @param liquidationDeadline
	 * @param deliveryDeadline
	 */
	@Builder
	OrderDto(Long id, String name, String code, @NotNull LocalDate orderDate, Long customerId, Long contactId,
			Long opportunityId, @NotNull Long orderValue, Long liquidationValue, @NotNull LocalDate liquidationDeadline,
			@NotNull LocalDate deliveryDeadline) {
		super(id, name, code);
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.contactId = contactId;
		this.opportunityId = opportunityId;
		this.orderValue = orderValue;
		this.liquidationValue = liquidationValue;
		this.liquidationDeadline = liquidationDeadline;
		this.deliveryDeadline = deliveryDeadline;
	}

}
