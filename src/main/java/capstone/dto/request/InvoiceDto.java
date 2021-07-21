/**
 * 
 */
package capstone.dto.request;

import capstone.dto.validatation.annotation.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Invoice Dto
 * Hóa đơn Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends CodedNamedDto<Long> {
	
	private Long customerId;
	
	private String address;
	
	private String bankAccount;
	
	private String bank;
	
	private String taxCode;
	
	private Long buyerId;
	
	@Email
	private String receiverName;
	
	private String receiverEmail;

	private String receiverPhone;
	
	private Long orderId;
	
}
