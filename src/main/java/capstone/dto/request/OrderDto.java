/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends CodedNamedDto<Long> {
	
	@NotNull
	private LocalDate orderDate;
	
	private Long customerId;
	
	private Long contactId;
 
	private Long opportunityId;

	@NotNull
	private Long orderValue;
	
	private Long liquidationValue;

	@NotNull
	private LocalDate liquidationDeadline;

	@NotNull
	private LocalDate deliveryDeadline;

}
